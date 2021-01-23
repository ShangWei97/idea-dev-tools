package com.shang.ui.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.shang.ui.IParserWidget;
import com.shang.ui.forms.ParserWindow;
import org.jetbrains.annotations.NotNull;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class NewWindowAction extends AnAction {

    private IParserWidget mParserWidget;
    private int count = 0;

    public NewWindowAction(IParserWidget iParserWidget) {
        super("Open in new window", "Open in new window", AllIcons.Actions.ChangeView);
        this.mParserWidget = iParserWidget;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {

        if (mParserWidget != null && mParserWidget.getComponent() != null) {
            ParserWindow window = new ParserWindow(mParserWidget, "json_parse" + count,count);
            count++;
            window.setWindowAdapter(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    if (count > 0) {
                        count--;
                    }
                }
            });
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        e.getPresentation().setEnabled(count <= 10);
    }
}
