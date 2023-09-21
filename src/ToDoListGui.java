import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGui extends JFrame implements ActionListener {

    private JPanel taskPanel, taskComponentPanel;

    public ToDoListGui (){
        super("To Do List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Config.GUI_SIZE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        addGuiComponent();
    }

    private void addGuiComponent(){
        JLabel bannerLabel = new JLabel("To Do List");
        bannerLabel.setBounds(
                (Config.GUI_SIZE.width - bannerLabel.getPreferredSize().width)/2,15,
                Config.BANNER_SIZE.width,
                Config.BANNER_SIZE.height
        );


        taskPanel = new JPanel();

        taskComponentPanel = new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel, BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);

        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBounds(8, 70, Config.TASKPANEL_SIZE.width, Config.TASKPANEL_SIZE.height);
        scrollPane.setMaximumSize(Config.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(-5 , Config.GUI_SIZE.height - 88,
                Config.ADDTASK_BUTTON_SIZE.width, Config.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();

        }
    }
}
