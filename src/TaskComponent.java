import javax.swing.*;
import java.awt.*;

public class TaskComponent extends JPanel {

    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() {
        return taskField;
    }
    private JPanel parentPanel;

    public TaskComponent(JPanel parentPanel){
        this.parentPanel = parentPanel;

        taskField = new JTextPane();
        taskField.setPreferredSize(Config.TASKFIELD_SIZE);
        taskField.setContentType("text/html");

        //checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(Config.CHECKBOX_SIZE);

        //delete button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(Config.DELETEBUTTON_SIZE);

        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

}
