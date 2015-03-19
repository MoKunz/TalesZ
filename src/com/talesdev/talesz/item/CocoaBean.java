
public class CocoaBean implements TalesZItem, MaxStackableInterface {
    @Override
    public void handleEvent(BlockBreakEvent event) {

    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public Material getType() {
        return Material.INK_SACK;
    }

    @Override
    public int getAmount() {
        return 1;
    }

    @Override
    public short getDurability() {
        return 3;
    }

    @Override
    public ItemMeta configItemMeta(ItemMeta itemMeta) {
        return itemMeta;
    }

    @Override
    public MaterialData configMaterialData(MaterialData materialData) {
        return null;
    }

    @Override
    public void handleEvent(InventoryClickEvent event) {

    }

    @Override
    public void handleEvent(PlayerInteractEntityEvent event) {

    }
    
    @Override
    public int getMaxStackSize() {
        return 3;
    }
}
