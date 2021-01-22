package com.shang.ui.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.shang.ui.IParserWidget;
import org.jetbrains.annotations.NotNull;


public class AddTabAction extends AnAction {

    private final IParserWidget mParserWidget;

    public AddTabAction(IParserWidget parserWidget) {
        super("Add Tab", "Create New ApiDebugger Tab", AllIcons.General.Add);
        mParserWidget = parserWidget;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        mParserWidget.createParserSession();
        System.out.println("AddTabAction.actionPerformed");
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        e.getPresentation().setEnabled(mParserWidget.getTabCount() <= 10);
    }
}
