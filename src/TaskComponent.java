import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskComponent extends JPanel implements ActionListener {

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
        checkBox.addActionListener(this);

        //delete button
        deleteButton = new JButton("X");
        deleteButton.setPreferredSize(Config.DELETEBUTTON_SIZE);

        add(checkBox);
        add(taskField);
        add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            // replaces all html tags to empty string to grab the main text
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            // add strikethrough text
            taskField.setText("<html><s>"+ taskText + "</s></html>");
        }else if(!checkBox.isSelected()){
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");

            taskField.setText(taskText);
        }

    }
}
