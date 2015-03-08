package org.test.chesstask.app;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessagesBundle {

    private ResourceBundle bundle;

    public void load() {
        bundle = ResourceBundle.getBundle("i18n/application", Locale.getDefault());
    }

    public String getInviteXMsg() {
        return bundle.getString("invite.x.msg");
    }

    public String getInviteYMsg() {
        return bundle.getString("invite.y.msg");
    }

    public String getInvitePieceMsg() {
        return bundle.getString("invite.piece.msg");
    }

    public String getIncorrectInputMsg(String input) {
        return getMessage("incorrect.input.msg", input);
    }

    public String getInvitePieceCountMsg() {
        return bundle.getString("invite.piece.num.msg");
    }

    public String getInviteMorePieceMsg() {
        return bundle.getString("invite.more.piece.msg");
    }

    public String getProcessingMsg(String input) {
        return getMessage("processing.msg", input);
    }

    public String getResultMsg(Long count, String input) {
        return getMessage("result.msg", count, input);
    }

    public String getMessage(String key, Object... input) {
        return String.format(bundle.getString(key), input);
    }
}
