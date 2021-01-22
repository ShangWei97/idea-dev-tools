package com.shang.ui.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.shang.ui.IParserWidget;
import org.jetbrains.annotations.NotNull;


public class CloseTabAction extends AnAction {
    private final IParserWidget mParserWidget;

    public CloseTabAction(IParserWidget parserWidget) {
        super("Close Tab", "Close ApiDebugger Session", AllIcons.Actions.Cancel);
        mParserWidget = parserWidget;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        mParserWidget.closeCurrentParserSession();
        System.out.println("CloseTabAction.actionPerformed");
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        e.getPresentation().setEnabled(mParserWidget.getTabCount() > 1);
    }
}