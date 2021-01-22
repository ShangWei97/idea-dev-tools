package com.shang.ui.forms;

import com.shang.ui.IParserWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ParserWindow extends JFrame {

    private IParserWidget mParserWidget;
    private WindowAdapter mWindowAdapter;
    private int count;

    public ParserWindow(IParserWidget parserWidget, String title, int count) {
        this.mParserWidget = parserWidget;
        this.count = count;

        setupView();
        setTitle(title);
    }

    public void setWindowAdapter(WindowAdapter mWindowAdapter) {
        this.mWindowAdapter = mWindowAdapter;
    }

    private void setupView() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = screenSize.width / 4;
        int height = screenSize.height / 2;

        height = height == 0 ? 500 : height;
        width = width == 0 ? 300 : width;

        setPreferredSize(new Dimension(width, height));
        add(mParserWidget.getNewComponent(), BorderLayout.CENTER);
        setSize(new Dimension(width, height));

        int locationWidth = (screenSize.width / 2 - width / 2) + count * 20;
        int locationHeight = (screenSize.height / 2 - height / 2) + count * 20;

        setLocation(locationWidth, locationHeight);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                if (mWindowAdapter != null) {
                    mWindowAdapter.windowClosing(e);
                }
            }
        });
        EventQueue.invokeLater(() -> {
            setVisible(true);
            toFront();
            repaint();
        });
    }
}
