package command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * コマンドを実行して動かすプラグイン処理の基底クラスです。
 */

public abstract class BaseCommand implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s,
      String[] strings) {
    if (commandSender instanceof Player player) {
      onExecutePlayerCommand(player, commandSender, command, s, strings);
      return true;
    } else {
      return onExecuteNPCCommand(commandSender, command, s,
          strings);
    }


  }

  /**
   * コマンド実行者がプレイヤーであれば、実行します。
   *
   * @param player  　コマンドを実行したプレイヤー
   * @param s       ラベル
   * @param strings 　コマンド引数
   * @param　command コマンド
   * @return　処理の実行有無
   */
  public abstract boolean onExecutePlayerCommand(Player player, CommandSender commandSender,
      Command command, String s,
      String[] strings);

  /**
   * コマンド実行者がプレイヤー以外であれば、実行します。
   *
   * @param command コマンド
   * @param s       ラベル
   * @param strings 　コマンド引数
   * @param　commandsender コマンド実行者
   * @return　処理の実行有無
   */
    public abstract boolean onExecuteNPCCommand(CommandSender commandSender, Command command,
        String s,
        String[] strings);

  public abstract boolean onExecuteNPCCommand(CommandSender commandSender);
}