package me.pr0crustes.frontend.gui.classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import me.pr0crustes.backend.exeptions.*;


public abstract class ActionController extends PassiveController implements Runnable {

    protected ActionController(Pane pane) {
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
            AlertFactory.DefinedAlert.invalidArgument.sendAlert();

        } catch (NoTargetFileException e) {
            // Ignore, user didn't selected a file
            //TODO: Handle this in some way (?)

        } catch (NoFileException e) {
            AlertFactory.DefinedAlert.noFile.sendAlert();

        } catch (PermissionException e) {
            AlertFactory.DefinedAlert.errorAtSave.sendAlert();

        } catch (Exception e) {
            e.printStackTrace();
            AlertFactory.DefinedAlert.unknownError.sendAlert();
        }
    }

    @SuppressWarnings("RedundantThrows")
    protected abstract void execute() throws
                                            ArgumentException,
                                            NoFileException,
                                            PermissionException,
                                            NoTargetFileException,
                                            StrangeException;
}
