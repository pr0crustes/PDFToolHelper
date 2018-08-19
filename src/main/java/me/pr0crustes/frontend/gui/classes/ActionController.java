package me.pr0crustes.frontend.gui.classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NullFileException;
import me.pr0crustes.backend.exeptions.StrangeException;

import java.io.IOException;

/**
 * ActionController is an abstract class that extends PassiveController (see it) and also
 * implements runnable, since actions should be done in another thread, to prevent the
 * GUI from not responding.
 */
public abstract class ActionController extends PassiveController implements Runnable {

    /**
     * Just a constructor that calls super.
     * See PassiveController constructor.
     * @param pane the Pane the GUI should be drawn on.
     */
    protected ActionController(Pane pane) {
        super(pane);
    }

    /**
     * Method that returns an EventHandler calling onClickDo
     * useful when creating buttons.
     * @return an EventHandler that calls onClickDo.
     */
    public EventHandler<ActionEvent> eventDo() {
        return (event -> this.onClickDo());
    }

    /**
     * Method that runs the run method in another thread.
     */
    private void onClickDo() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Method run, implementing runnable.
     * Allowing code to run in another thread.
     * This method just calls runExecute.
     * RunExecute is another method just to encapsulate things.
     */
    public void run() {
        this.runExecute();
    }

    /**
     * One of the most important methods, runExecute runs execute in a try catch,
     * checking exceptions and handling them.
     */
    private void runExecute() {
        try {
            this.execute();
        } catch (ArgumentException e) {
            e.printStackTrace();
            AlertFactory.DefinedAlert.invalidArgument.sendAlert();
        } catch (NullFileException e) {
            e.printStackTrace();
            // Ignore, user didn't selected a file
            //TODO: Handle this in some way (?)
        } catch (IOException e) {
            e.printStackTrace();
            AlertFactory.DefinedAlert.fileError.sendAlert();
        } catch (Exception e) {
            e.printStackTrace();
            AlertFactory.DefinedAlert.unknownError.sendAlert();
        }
    }

    /**
     * Execute is an abstract method that subclasses need to implement.
     * This method can throw exceptions and because of this is mainly called from runExecute.
     * @throws ArgumentException in case of invalid arguments.
     * @throws IOException in case a file related error.
     * @throws NullFileException in case the file is null.
     * @throws StrangeException in case of a strange error.
     */
    @SuppressWarnings("RedundantThrows")
    protected abstract void execute() throws ArgumentException, IOException, NullFileException, StrangeException;

}
