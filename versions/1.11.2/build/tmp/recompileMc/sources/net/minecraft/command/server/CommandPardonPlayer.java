package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandPardonPlayer extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getName()
    {
        return "pardon";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 3;
    }

    /**
     * Gets the usage string for the command.
     *  
     * @param sender The ICommandSender who is requesting usage details
     */
    public String getUsage(ICommandSender sender)
    {
        return "commands.unban.usage";
    }

    /**
     * Check if the given ICommandSender has permission to execute this command
     *  
     * @param server The server instance
     * @param sender The ICommandSender to check permissions on
     */
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return server.getPlayerList().getBannedPlayers().isLanServer() && super.checkPermission(server, sender);
    }

    /**
     * Callback for when the command is executed
     *  
     * @param server The server instance
     * @param sender The sender who executed the command
     * @param args The arguments that were passed
     */
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length == 1 && args[0].length() > 0)
        {
            GameProfile gameprofile = server.getPlayerList().getBannedPlayers().getBannedProfile(args[0]);

            if (gameprofile == null)
            {
                throw new CommandException("commands.unban.failed", new Object[] {args[0]});
            }
            else
            {
                server.getPlayerList().getBannedPlayers().removeEntry(gameprofile);
                notifyCommandListener(sender, this, "commands.unban.success", new Object[] {args[0]});
            }
        }
        else
        {
            throw new WrongUsageException("commands.unban.usage", new Object[0]);
        }
    }

    /**
     * Get a list of options for when the user presses the TAB key
     *  
     * @param server The server instance
     * @param sender The ICommandSender to get tab completions for
     * @param args Any arguments that were present when TAB was pressed
     * @param targetPos The block that the player's mouse is over, <tt>null</tt> if the mouse is not over a block
     */
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos)
    {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getPlayerList().getBannedPlayers().getKeys()) : Collections.<String>emptyList();
    }
}