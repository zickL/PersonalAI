package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChatUI extends JFrame {

    private JPanel chatPanel;
    private JTextField inputField;
    private JButton sendButton;
    private ChatService chatService;
    private JScrollPane scrollPane;

    public ChatUI(ChatService chatService) {
        this.chatService = chatService;

        setTitle("Chat Anything");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        chatPanel.setBackground(Color.WHITE);
        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8);
        add(scrollPane, BorderLayout.CENTER);


        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(350, 50));
        ImageIcon sendIcon = new ImageIcon("/Users/zickyli/Downloads/sendIcon.png");
        sendButton = new JButton(sendIcon);
        sendButton.setPreferredSize(new Dimension(30, 30));
        sendButton.setBorderPainted(false);
        sendButton.setContentAreaFilled(false);

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        loadChatHistory();
    }
    private void loadChatHistory() {
        for (String message : chatService.getPreviousMessages()) {
            addMessageToChat(message, true);
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input message", "WARNING", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!message.isEmpty()) {

            addMessageToChat(message, true);
            inputField.setText("");
            String response = chatService.sendMessage(message);
            addMessageToChat("OPENAI: " + response, false);
            SwingUtilities.invokeLater(() -> {
                JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            });
        }
    }


    private void addMessageToChat(String message, boolean isUser) {
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setOpaque(false);

        JTextArea messageArea = new JTextArea(message);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        messageArea.setOpaque(true);
        messageArea.setBackground(isUser ? new Color(220, 248, 198) : new Color(240, 240, 240));
        messageArea.setForeground(Color.BLACK);
        messageArea.setFont(new Font("Arial", Font.PLAIN, 14));
        messageArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // 设置首选大小以限制宽度
        int maxWidth = this.getWidth() - 40; // 留出一些边距
        messageArea.setSize(maxWidth, Short.MAX_VALUE);
        int preferredHeight = messageArea.getPreferredSize().height;
        messageArea.setPreferredSize(new Dimension(maxWidth, preferredHeight));


        if (isUser) {
            messagePanel.add(messageArea, BorderLayout.EAST);
        } else {
            messagePanel.add(messageArea, BorderLayout.WEST);
        }

        chatPanel.add(Box.createVerticalStrut(10));
        chatPanel.add(messagePanel);
        chatPanel.revalidate();
        chatPanel.repaint();

        // 滚动到底部
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (Component comp : chatPanel.getComponents()) {
                    if (comp instanceof JPanel) {
                        JPanel messagePanel = (JPanel) comp;
                        for (Component innerComp : messagePanel.getComponents()) {
                            if (innerComp instanceof JScrollPane) {
                                JScrollPane scrollPane = (JScrollPane) innerComp;
                                JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
                                int maxWidth = ChatUI.this.getWidth() - 50;
                                textArea.setPreferredSize(new Dimension(maxWidth, 1));
                                textArea.revalidate();
                            }
                        }
                    }
                }
                chatPanel.revalidate();
            }
        });
    }

}