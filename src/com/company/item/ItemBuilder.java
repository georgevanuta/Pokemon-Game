package com.company.item;

public class ItemBuilder {
    private Item item = new Item();

    public Item build() {
        return this.item;
    }

    public ItemBuilder withName(String name) {
        this.item.setName(name);
        return this;
    }

    public ItemBuilder withIncHp(Integer incHp) {
        this.item.setIncHp(incHp);
        return this;
    }

    public ItemBuilder withIncAttack(Integer incAttack) {
        this.item.setIncAttack(incAttack);
        return this;
    }

    public ItemBuilder withIncSpecialAttack(Integer incSpecialAttack) {
        this.item.setIncSpecialAttack(incSpecialAttack);
        return this;
    }

    public ItemBuilder withIncDef(Integer incDef) {
        this.item.setIncDefense(incDef);
        return this;
    }

    public ItemBuilder withIncSpecialDef(Integer incSpecialDef) {
        this.item.setIncSpecialAttack(incSpecialDef);
        return this;
    }
}
