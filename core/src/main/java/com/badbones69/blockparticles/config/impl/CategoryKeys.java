package com.badbones69.blockparticles.config.impl;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;
import com.badbones69.blockparticles.api.objects.Categories;
import static ch.jalu.configme.properties.PropertyInitializer.newBeanProperty;

public class CategoryKeys implements SettingsHolder {

    @Comment("A collection of configurable heads!")
    public static final Property<Categories> heads = newBeanProperty(Categories.class, "categories", new Categories().populate());

}