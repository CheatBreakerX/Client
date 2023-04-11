package net.minecraft.command;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class CommandDifficulty extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getName()
    {
        return "difficulty";
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
     *  
     * @param sender The ICommandSender who is requesting usage details
     */
    public String getUsage(ICommandSender sender)
    {
        return "commands.difficulty.usage";
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
        if (args.length <= 0)
        {
            throw new WrongUsageException("commands.difficulty.usage", new Object[0]);
        }
        else
        {
            EnumDifficulty enumdifficulty = this.getDifficultyFromCommand(args[0]);
            server.setDifficultyForAllWorlds(enumdifficulty);
            notifyCommandListener(sender, this, "commands.difficulty.success", new Object[] {new TextComponentTranslation(enumdifficulty.getDifficultyResourceKey(), new Object[0])});
        }
    }

    protected EnumDifficulty getDifficultyFromCommand(String difficultyString) throws CommandException, NumberInvalidException
    {
        return !"peaceful".equalsIgnoreCase(difficultyString) && !"p".equalsIgnoreCase(difficultyString) ? (!"easy".equalsIgnoreCase(difficultyString) && !"e".equalsIgnoreCase(difficultyString) ? (!"normal".equalsIgnoreCase(difficultyString) && !"n".equalsIgnoreCase(difficultyString) ? (!"hard".equalsIgnoreCase(difficultyString) && !"h".equalsIgnoreCase(difficultyString) ? EnumDifficulty.getDifficultyEnum(parseInt(difficultyString, 0, 3)) : EnumDifficulty.HARD) : EnumDifficulty.NORMAL) : EnumDifficulty.EASY) : EnumDifficulty.PEACEFUL;
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
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[] {"peaceful", "easy", "normal", "hard"}): Collections.<String>emptyList();
    }
}