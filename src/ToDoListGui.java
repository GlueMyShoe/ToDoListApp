import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        bannerLabel.setFont(createFont("Resources/LEMONMILK-Light.otf", 36f));
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
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(Config.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFocusable(false);
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setFont(createFont("Resources/LEMONMILK-Light.otf", 18f));
        addTaskButton.setBounds(-5 , Config.GUI_SIZE.height - 88,
                Config.ADDTASK_BUTTON_SIZE.width, Config.ADDTASK_BUTTON_SIZE.height);
        addTaskButton.addActionListener(this);

        this.getContentPane().add(bannerLabel);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);

    }

    private Font createFont(String Resources, float size) {
        String filePath = getClass().getClassLoader().getResource(Resources).getPath();

        if (filePath.contains("%20")) {
            filePath = getClass().getClassLoader().getResource(Resources).getPath()
                    .replaceAll("%20", " ");

        }

        try {
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile).deriveFont(size);
            return customFont;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            TaskComponent taskComponent = new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            if(taskComponentPanel.getComponentCount() > 1){
                TaskComponent previousTask = (TaskComponent) taskComponentPanel.getComponent(
                        taskComponentPanel.getComponentCount() - 2);
                previousTask.getTaskField().setBackground(null);

            }

            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();

        }
    }
}
