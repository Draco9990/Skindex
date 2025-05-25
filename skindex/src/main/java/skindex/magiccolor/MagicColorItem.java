package skindex.magiccolor;

import skindex.itemtypes.AbstractCustomizableItem;
import skindex.itemtypes.AbstractOwnableItem;

public class MagicColorItem extends AbstractOwnableItem {
    public MagicColorItem(OwnableItemData itemData) {
        super(itemData);
    }

    @Override
    protected Class<? extends AbstractCustomizableItem> getItemType() {
        return MagicColorItem.class;
    }
}
