package data;

import com.google.common.collect.ForwardingSet;

import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    @Override
    protected Set<GroupData> delegate() {
        return null;
    }
}
