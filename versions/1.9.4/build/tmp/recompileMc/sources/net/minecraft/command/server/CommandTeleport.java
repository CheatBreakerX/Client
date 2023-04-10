package net.minecraft.command.server;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class CommandTeleport extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getCommandName()
    {
        return "tp";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    /**
     * Gets the usage string for the command.
     */
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.tp.usage";
    }

    /**
     * Callback for when the command is executed
     */
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1)
        {
            throw new WrongUsageException("commands.tp.usage", new Object[0]);
        }
        else
        {
            int i = 0;
            Entity entity;

            if (args.length != 2 && args.length != 4 && args.length != 6)
            {
                entity = getCommandSenderAsPlayer(sender);
            }
            else
            {
                entity = getEntity(server, sender, args[0]);
                i = 1;
            }

            if (args.length != 1 && args.length != 2)
            {
                if (args.length < i + 3)
                {
                    throw new WrongUsageException("commands.tp.usage", new Object[0]);
                }
                else if (entity.worldObj != null)
                {
                    int lvt_6_2_ = i + 1;
                    CommandBase.CoordinateArg commandbase$coordinatearg = parseCoordinate(entity.posX, args[i], true);
                    CommandBase.CoordinateArg commandbase$coordinatearg1 = parseCoordinate(entity.posY, args[lvt_6_2_++], -512, 512, false);
                    CommandBase.CoordinateArg commandbase$coordinatearg2 = parseCoordinate(entity.posZ, args[lvt_6_2_++], true);
                    CommandBase.CoordinateArg commandbase$coordinatearg3 = parseCoordinate((double)entity.rotationYaw, args.length > lvt_6_2_ ? args[lvt_6_2_++] : "~", false);
                    CommandBase.CoordinateArg commandbase$coordinatearg4 = parseCoordinate((double)entity.rotationPitch, args.length > lvt_6_2_ ? args[lvt_6_2_] : "~", false);

                    if (entity instanceof EntityPlayerMP)
                    {
                        Set<SPacketPlayerPosLook.EnumFlags> set = EnumSet.<SPacketPlayerPosLook.EnumFlags>noneOf(SPacketPlayerPosLook.EnumFlags.class);

                        if (commandbase$coordinatearg.isRelative())
                        {
                            set.add(SPacketPlayerPosLook.EnumFlags.X);
                        }

                        if (commandbase$coordinatearg1.isRelative())
                        {
                            set.add(SPacketPlayerPosLook.EnumFlags.Y);
                        }

                        if (commandbase$coordinatearg2.isRelative())
                        {
                            set.add(SPacketPlayerPosLook.EnumFlags.Z);
                        }

                        if (commandbase$coordinatearg4.isRelative())
                        {
                            set.add(SPacketPlayerPosLook.EnumFlags.X_ROT);
                        }

                        if (commandbase$coordinatearg3.isRelative())
                        {
                            set.add(SPacketPlayerPosLook.EnumFlags.Y_ROT);
                        }

                        float f = (float)commandbase$coordinatearg3.getAmount();

                        if (!commandbase$coordinatearg3.isRelative())
                        {
                            f = MathHelper.wrapDegrees(f);
                        }

                        float f1 = (float)commandbase$coordinatearg4.getAmount();

                        if (!commandbase$coordinatearg4.isRelative())
                        {
                            f1 = MathHelper.wrapDegrees(f1);
                        }

                        entity.dismountRidingEntity();
                        ((EntityPlayerMP)entity).connection.setPlayerLocation(commandbase$coordinatearg.getAmount(), commandbase$coordinatearg1.getAmount(), commandbase$coordinatearg2.getAmount(), f, f1, set);
                        entity.setRotationYawHead(f);
                    }
                    else
                    {
                        float f2 = (float)MathHelper.wrapDegrees(commandbase$coordinatearg3.getResult());
                        float f3 = (float)MathHelper.wrapDegrees(commandbase$coordinatearg4.getResult());
                        f3 = MathHelper.clamp_float(f3, -90.0F, 90.0F);
                        entity.setLocationAndAngles(commandbase$coordinatearg.getResult(), commandbase$coordinatearg1.getResult(), commandbase$coordinatearg2.getResult(), f2, f3);
                        entity.setRotationYawHead(f2);
                    }

                    notifyCommandListener(sender, this, "commands.tp.success.coordinates", new Object[] {entity.getName(), Double.valueOf(commandbase$coordinatearg.getResult()), Double.valueOf(commandbase$coordinatearg1.getResult()), Double.valueOf(commandbase$coordinatearg2.getResult())});
                }
            }
            else
            {
                Entity entity1 = getEntity(server, sender, args[args.length - 1]);

                if (entity1.worldObj != entity.worldObj)
                {
                    throw new CommandException("commands.tp.notSameDimension", new Object[0]);
                }
                else
                {
                    entity.dismountRidingEntity();

                    if (entity instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)entity).connection.setPlayerLocation(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);
                    }
                    else
                    {
                        entity.setLocationAndAngles(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);
                    }

                    notifyCommandListener(sender, this, "commands.tp.success", new Object[] {entity.getName(), entity1.getName()});
                }
            }
        }
    }

    /**
     * Get a list of options for when the user presses the TAB key
     */
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos)
    {
        return args.length != 1 && args.length != 2 ? Collections.<String>emptyList() : getListOfStringsMatchingLastWord(args, server.getAllUsernames());
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] args, int index)
    {
        return index == 0;
    }
}