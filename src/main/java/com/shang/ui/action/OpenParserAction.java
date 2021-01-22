package com.shang.ui.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.shang.common.Logger;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

public class OpenParserAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Editor mEditor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            Logger.i("Not in editor window");
            return;
        }

        SelectionModel model = mEditor.getSelectionModel();

        String selectText = model.getSelectedText();


        if (TextUtils.isEmpty(selectText)) {
            Logger.i("selectText == null");
            return;
        }
        Logger.i("selectText: " + selectText);
    }
}
