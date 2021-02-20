package de.insanitydev.itembuilder;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;
import java.util.UUID;

public final class ItemBuilder {

    private final ItemStack itemStack;
    @Getter
    private ItemMeta itemMeta;

    /**
     * @param material The {@link Material} to use for the ItemStack.
     */
    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material, 1);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = itemStack.getItemMeta();
    }

    /**
     * @param itemStack The {@link ItemStack} to use.
     */
    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    /**
     * @param amount The amount of the ItemStack
     * @return ItemBuilder
     */
    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * @param enchantmentIntegerMap A map with enchants to add to a ItemStack
     * @return ItemBuilder
     */
    public ItemBuilder addEnchants(Map<Enchantment, Integer> enchantmentIntegerMap) {
        itemStack.addEnchantments(enchantmentIntegerMap);
        return this;
    }

    public ItemBuilder addEnchantsUnsafe(Map<Enchantment, Integer> enchantmentIntegerMap) {
        itemStack.addUnsafeEnchantments(enchantmentIntegerMap);
        return this;
    }

    public ItemBuilder setName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.itemMeta.setLore(Lists.newArrayList(lore));
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder setItemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchant, int level) {
        this.itemStack.addEnchantment(enchant, level);
        return this;
    }

    public ItemBuilder addEnchant(Enchantment enchant, int level, boolean ignoreRestriction) {
        this.itemMeta.addEnchant(enchant, level, ignoreRestriction);
        return this;
    }

    public ItemBuilder setArmorValue(double armorValue, double armorToughness, EquipmentSlot slot) {
        this.itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,
                new AttributeModifier(UUID.randomUUID(), "generic_armor", armorValue, AttributeModifier.Operation.ADD_NUMBER, slot));
        this.itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,
                new AttributeModifier(UUID.randomUUID(), "generic_armor_toughness", armorToughness, AttributeModifier.Operation.ADD_NUMBER, slot));
        return this;
    }

    public ItemBuilder setKnockbackResistance(double resistance, EquipmentSlot slot) {
        this.itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE,
                new AttributeModifier(UUID.randomUUID(), "generic_knockback_resistance", resistance, AttributeModifier.Operation.ADD_NUMBER, slot));
        return this;
    }

    public PersistentDataContainer getPersistentDataContainer() {
        return this.itemMeta.getPersistentDataContainer();
    }

    public ItemBuilder setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    public <T, Z> ItemBuilder setPersistentDataContainer(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        this.itemMeta.getPersistentDataContainer().set(key, type, value);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
