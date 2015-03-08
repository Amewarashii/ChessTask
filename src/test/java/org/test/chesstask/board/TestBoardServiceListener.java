package org.test.chesstask.board;

import java.util.*;

public class TestBoardServiceListener implements BoardServiceListener {

    private Set<String> items = new HashSet<String>();

    @Override
    public void onItem(BoardState state) {
        items.add(state.toString());
    }

    public Collection<String> getItems() {
        return items;
    }
}
