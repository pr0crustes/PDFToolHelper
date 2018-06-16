package me.pr0crustes.frontend.gui.classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import me.pr0crustes.backend.exeptions.ArgumentException;
import me.pr0crustes.backend.exeptions.NoFileException;
import me.pr0crustes.backend.exeptions.PermissionException;
import me.pr0crustes.backend.exeptions.StrangeException;


public abstract class ActionController extends PassiveController implements Runnable {

    public ActionController(Pane pane) {
        super(pane);
    }

    public EventHandler<ActionEvent> eventDo() {
        return (event -> this.onClickDo());
    }

    private void onClickDo() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        this.runExecute();
    }

    private void runExecute() {
        try {
            this.execute();

        } catch (ArgumentException e) {
            e.printStackTrace();
            AlertFactory.DefinedAlert.invalidArgument.sendAlert();

        } catch (NoFileException e) {
            AlertFactory.DefinedAlert.noFile.sendAlert();

        } catch (PermissionException e) {
            AlertFactory.DefinedAlert.errorAtSave.sendAlert();

        } catch (Exception e) {
            e.printStackTrace();
            AlertFactory.DefinedAlert.unknowError.sendAlert();
        }
    }

    protected abstract void execute() throws ArgumentException, NoFileException, PermissionException, StrangeException;
}
