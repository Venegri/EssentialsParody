package ru.venegri.essentials.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("deop")) {
            try {
                if (!commandSender.hasPermission("brou.deop")) {
                    commandSender.sendMessage(ChatColor.RED + "You don't have permission.");
                } else {
                    String deopPlayer = strings[0];
                    if (strings.length == 0) {
                        return false;
                    }
                    try {
                        Player playerToDeOp = Bukkit.getServer().getPlayer(deopPlayer);
                        if(playerToDeOp.isOnline()) {
                            if(!playerToDeOp.isOp()) {
                                commandSender.sendMessage(ChatColor.RED + "У игрока нет опки!");
                            } else {
                                playerToDeOp.sendMessage("Вы были деопнуты администратором " + commandSender.getName());
                                Bukkit.broadcastMessage(ChatColor.RED + (playerToDeOp.getName() + " был де/опнут на сервере администратором " + commandSender.getName()));
                                playerToDeOp.setOp(false);
                            }
                        } else {
                            commandSender.sendMessage("Данный игрок не онлайн.");
                        }
                        return true;
                    } catch (Exception ex) {
                        commandSender.sendMessage("Данный игрок не онлайн.");
                    }
                }
            } catch (Exception ex) {
                return false;
            }

        }
        return true;
    }
}

