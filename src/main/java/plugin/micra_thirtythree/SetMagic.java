package plugin.micra_thirtythree;

import java.util.Date;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import java.text.SimpleDateFormat;


// SetMagicで、色んな魔法を設定する
public class SetMagic implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s,
      String[] strings) {
    if (!(commandSender instanceof Player player)) {
      commandSender.sendMessage("プレイヤーのみ魔法コマンドが使用可能");
      return false;
    }

    if (strings.length == 0) {
      player.sendMessage("使用方法→  /magic 魔法名");
      return false;
    }

//    魔法の種類
    switch (strings[0].toLowerCase(

    )) {

//      炎攻撃
      case "fireball":
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection().multiply(2);
        Fireball fireball = player.getWorld().spawn(eyeLocation.add(direction), Fireball.class);
        fireball.setShooter(player);
        player.sendMessage(ChatColor.RED + "ファイヤーボール発動");
        break;

      // ゴーレムもしくは、ニワトリを召喚
      case "summon":
        if (strings.length > 1) {
          switch (strings[1].toLowerCase()) {
//            ゴーレム召喚
            case "golem":
              IronGolem golem = (IronGolem) player.getWorld()
                  .spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
              golem.setCustomName(ChatColor.GRAY + player.getName() + "のゴーレム");
              player.sendMessage(ChatColor.GRAY + "ゴーレムを召喚");
              break;
//              ニワトリを召喚
            case "niwatori":
              Chicken niwatori = (Chicken) player.getWorld()
                  .spawnEntity(player.getLocation(), EntityType.CHICKEN);
              niwatori.setCustomName(ChatColor.GRAY + player.getName() + "のニワトリ");
              player.sendMessage(ChatColor.GRAY + "ニワトリを召喚");
              break;
//              召喚無効
            default:
              player.sendMessage(ChatColor.RED + "召喚無効。");
              break;
          }
        } else {
          player.sendMessage(
              ChatColor.RED + "召喚タイプを指定してください。例: /magic summon golem");
        }
        break;

//         体力回復
      case "cure":
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
        player.sendMessage(ChatColor.GREEN + "体力を回復しました。");
        break;

//        状態異常回復
      case "recovery":
        player.getActivePotionEffects()
            .forEach(effect -> player.removePotionEffect(effect.getType()));
        player.sendMessage(ChatColor.GREEN + "状態異常を回復しました");
        break;

//        スピードアップ
      case "speed":
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 2));
        player.sendMessage(ChatColor.BLUE + "スピードアップ");
        break;

//        防御力アップ
      case "defense":
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 600, 2));
        player.sendMessage(ChatColor.AQUA + "防御力アップ");
        break;

//        空を飛ぶ
      case "fly":
        player.setAllowFlight(true);
        player.sendMessage(ChatColor.GOLD + "空を飛ぶ");
        break;

//        透明化
      case "invisibility":
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 600, 1));
        player.sendMessage(ChatColor.WHITE + "透明化");

//        盾を入手
      case "shield":
        player.getInventory().addItem(new ItemStack(Material.SHIELD));
        player.sendMessage(ChatColor.AQUA + "盾を装備");
        break;
//        剣を入手
      case "sword":
        player.getInventory().addItem(new ItemStack(Material.NETHERITE_SWORD));
        player.sendMessage(ChatColor.DARK_PURPLE + "剣を装備");
        break;

//        現在の時間
      case "time":
        String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        player.sendMessage("現在の時間: " + currentTime);
        break;

//        現在位置
      case "currentlocation":
        Location loc = player.getLocation();
        player.sendMessage(
            ChatColor.RED + "現在位置Xは、 " + loc.getBlockX() + "現在位置Yは、 " + loc.getBlockY()
                + "現在位置Zは、 " + loc.getBlockZ());
        break;

      //      雷攻撃 ※自滅魔法
      case "lightning":
        Location location = player.getLocation();
        player.getWorld().strikeLightning(location);
        player.sendMessage(ChatColor.YELLOW + "雷発動");
        break;

//      プレーヤー自身が数秒で自滅 ※自滅魔法
      case "damage":
        player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 600, 1));
        player.sendMessage(ChatColor.RED + "プレーヤーは、自滅しました。");
        break;

//      プレーヤー自身が、毒状態になる。※自滅魔法
      case "poison":
        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 1));
        player.sendMessage(ChatColor.DARK_PURPLE + "毒に侵されました！");
        break;

//     プレーヤー自身の視界が狭くなる。
      case "blind":
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 1));
        player.sendMessage(ChatColor.BLACK + "視界が狭くなりました。");

//        覚えていない魔法
      default:
        player.sendMessage(ChatColor.DARK_RED + "覚えてない魔法です。");
        return false;

    }

    return false;
  }


}
