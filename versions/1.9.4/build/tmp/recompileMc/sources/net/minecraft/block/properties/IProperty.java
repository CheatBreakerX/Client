package net.minecraft.block.properties;

import com.google.common.base.Optional;
import java.util.Collection;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IProperty<T extends Comparable<T>>
{
    String getName();

    Collection<T> getAllowedValues();

    /**
     * The class of the values of this property
     */
    Class<T> getValueClass();

    @SideOnly(Side.CLIENT)
    Optional<T> parseValue(String value);

    /**
     * Get the name for the given value.
     */
    String getName(T value);
}