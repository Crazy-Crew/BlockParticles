package com.badbones69.blockparticles.listeners;

import com.badbones69.blockparticles.BlockParticles;
import com.badbones69.blockparticles.api.objects.CustomFountain;
import com.badbones69.blockparticles.Methods;
import com.badbones69.blockparticles.api.ParticleManager;
import com.badbones69.blockparticles.hooks.HeadDatabaseHook;
import com.ryderbelserion.vital.paper.util.scheduler.FoliaRunnable;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FountainListener implements Listener {

    private static final BlockParticles plugin = BlockParticles.getPlugin();

    private static final Server server = plugin.getServer();

    private static final ParticleManager particleManager = plugin.getParticleManager();
    
    private static final Random random = new Random();

    private static final List<String> pokemonHeads = Arrays.asList(
            "4a786e4e35b59d91eb6454ef26b7b0683761d6b11f1d63c7740af17aa3f",
            "f4a224d1753fddd25f5bd6b4b6ac879efdb4e978c046e7fa8120a07a8e4ab4d8",
            "e4c65312f2c539f682d1d5c482c9f494c9c91c538c5e2b887bdff8fcd9c3f",
            "377d1e20cdaee91d6d6059428d9f54879acb2662991e5150e2a373175f76374",
            "bf58bf9f81637d364eae71037aa0a5c4c3a46b21697a6bdd1d1f653f5a",
            "53a22e02fcbdf52363687ae345f93e0e44cc65fa7f97b78a44af334fff41e",
            "dd53bd826f1c1f331b6b8ed8383b22b5e821c5458e9ba671ddaec4ed0c8d13",
            "a644660e54cc1fe315a99b94e199115c54cd77cbf7c6aef2470dbef4f68f327",
            "406051fc28fcfdbefb543ad78a2b254b254dd6f171c7346b46a46dd3923f",
            "93e1faa993a47bda9bc7de0c693ca6c82726626bd25a7c064d7af779636a",
            "20dc8af9cc68ff1f2d7b4c680751f20ddcc20f1663ecc902b5d2b4f7b74d1f6",
            "d4ee7ed3f6eddc1017ab8cb5583e17fb7279d656a9da0c2838db36d217d39",
            "da4aee3f52e827185b9b982f5fa654fcbdda3657261ce7b5314c1b2576a8a85",
            "7a4fa71ad28cd1e1b7ea93581730825cba96cac3cd3b1bc72a97ea54de534",
            "42621d766c4e69f85928be4ceda0b996e95f5a20fe96232bd02ed42750d",
            "af10c0af26e762ebcd71bbb091afcfa26885246c87c32ab515538b8ab8d5d481",
            "1ea08949f673f93e61b990f4c1fe6043be7c942dc304122ebcd43808ff5e6",
            "e7a5e52183e41b28de41d9038883d399dc587d4eb230e696d8f6be6d3e57cf",
            "b161bf63f23b3e7f7541b6fbc83dc9d2168eca193c1c1ab4cc6ededf4e33e",
            "2440972f1dcb244872d2f1026daceb94dadb9851ca5932e15154ffe7e3be8",
            "dfa695b59618b3616b6aaa910c5a10146195edd6367d25e9399a74ceef966",
            "abf523f2bd90b3ff1944515b6a324338aad47ea1f2ce93f82d5564c4c9ade71",
            "e2f3f9cca77c725217e45ad4eeeeffa0565f82b866ac67999b43c3a97311628c",
            "8aa253fadd897a6a19aad3959c44fb4ceac5a8ca588f10e52ec8cfbb4144c6d",
            "c4bd89dc9528ce2d9c297254c3205061951eb6b2f063ae84dafcd4ef7978",
            "b3de38a1ceea6d9493df19a8b55bb238711cd5da4f435d2ec026376d874647",
            "5621982280472e7bb07909e38f6f36756bcc87dcb219c97d14467c9877e4e9",
            "dc23586f51fc98b55450bb9a37a066caac2765c1d471cb891094a8ec032befb",
            "7a8b87e46cfe8a2dc3525c1c67d8a69a25fd0a7f724a6fa911ad74adb6d82c2",
            "9ebeec5cefd4fac3d2585748ebca98b334c99b29315e4f6283138fb13e3f38",
            "77dfa8f0cc91b5d814a185c5e80b5dc5cac198113b1e9ed87386c99833999f",
            "ab11142d5afee8952569ac548e7d0e77821692ce6d12ce0b490924b394b44e4",
            "94bf92e38eb62fbbc1107715b4b8de27912c86a58694984962713b589658da",
            "434b218e41d875a7c78e5e2821e69b2e83b09fbade556ea8af3f2a5312d5cbc3",
            "9d2715a338f14cde38f2a4ff7580e05ab7976a8f7483e39f83cef7bd432b2dd",
            "b591e223d2fdd276548117d78a880e1292a2f327be7ef4d26af514fe4af7a69",
            "3712431fd86c7e1da4924cbb25d8e3ba0fd1a1e3e69228677d5235edd4f82f",
            "756ac84299d37f124cbb511e949edcd5281792b5dbf2ff4e5a481b94c5494d",
            "4a40ca8e3ef2b3f6a13680568fda3f43cfe395a666c1f5586313ab41221187a",
            "cc6cb189b246bd9a92328458039b4132297dfd93d845487abea09e926fa",
            "787b77ea380bc58faeadfb3722d77da87934051fdc6b4292ce5dcd0ab3c88ee5",
            "17c2b46be72c2320a698795902856762e0cadf1a953b22c846b5c650acb456ac",
            "ede4914a42479a74c2c18679c30010bb8983377ccb1308c22b837dd8587145f6",
            "d070410b81285d29edec97d5489ba8d5a3540a92d3b592fa1d8da0b12a4a7101",
            "57e1751aa2db43adbb5c8d1534b8e6f64d42a81acfdb25efaa46be23bccde4b7",
            "c5ea93557401e05432aebf876f91250154e7a784287b1616a4e72977c570ffa0",
            "e3a88146def8154a3a913a5b2ab72b625bb20c74c5461f00a29b3c0ae732ec58",
            "ca88655b16a8331a8d130c172243ce18ef7d28573ccd3c9faee464be6fdaf346",
            "7cd6fcbf8004cd69960bddda66e5a2e44c607815d360c7c58ecbc610e196cd91",
            "8fb261fd3eb2138fbcafa80301311daec78aadc107760ec02fb53ef2d572da94",
            "412718f386563874d4126dcf34f6dfb4db89fdd14b1ad37bbe1678bd3edd2");
    private static final List<String> marioHeads = Arrays.asList(
            "5fc8b863995fb84685c273c859548c75d94f9b82cce41b1efff454fe03cc123",
            "70731ba2213defc3988da53ae0c6267d17b994758071814b8731bbd01178972",
            "4b92cb43333aa621c70eef4ebf299ba412b446fe12e341ccc582f3192189",
            "3721ff1554bc61b47cc53a98246ecfddf3531785188183c5941d555d16c6af2",
            "a557187a729e21d1e6b3cdf083aad2a646efaca2f489dac1b2fa324a4a7d9",
            "aecf94f4bcbbf6eadcb25aa3d069aa678ebdb5241eb82e8e26889caf3275570",
            "b77cbf22ba61a0a1aa62d234fc0fb6c379d752d54de48efb2c2efa5e3ff81",
            "607356f6447318f73439b26293ea4b50cc64d69f8ef3317d78675075b2f9ecda",
            "9e373ba9c720aded6a810478e5e74c7f7096811bf9a3c3923de9b99ef992cc",
            "d1bfa814322822cd4335b8e952f4a84c3943f5f82d9a898f15fcbf1fbf225137",
            "29dfe7c2dabe04b9415402e2c36c4ed80fa6ccebc8b0c3a000c3c4b46bb5b998",
            "fe35d96a6d6786fbc1cd232c3556f8a857bdeb278bd662ee387a9a3b1174e4a7",
            "f37af4c8e21cebd262b7b00fb9ee52fa56e89a37b0f849f8ef7f4c7bf34c6236",
            "5e7981347b3cff63334002c914bf0420f4560c3abcd7e47bb2d0bbe3a92c25bb",
            "52cc46c2f0ed37b43b6e0f4fb19cf6f200d3348ccd36bad2266191e3de550b65",
            "4767b56f50d5ca97f7f1420c33fc7737dc3564315e8ec0271beb2c4be97b3625",
            "877d43ba37c6ca6ccd7f6af220949902425d5ea65cc709425b5ecc493fae5ac5");
    private static final List<String> foodHeads = Arrays.asList(
            "c5e27988a6538010efc0e24756bc3e3eea24d7536b20932c17e0404e5cc55f",
            "b8fb0502a3aa5f8bd32a5ea5e519c3dd353234170dfef959ee8adb9487fea",
            "2db75cde39ee20e8d8b47f3ba3157caef77d5609b6dd14e10434adf2b4a528b",
            "a8fa562856959dfefbc1328cdfd9d430b65af7f24cf326400767305b34c5b2e5",
            "82c5178adc4ffcf64d4c68f7549a751ac02a317540bc2fe65d17f01f9682ddf1",
            "693b57ba4ac6f04315fc481de7a21ef27ecb48bab24358a2ec722020dc5fc85e",
            "fe3052c535e14597a413ec32b32aafdd28686fdab6eed73030e1b94f7c38ff",
            "2f6d72e39aec77cd61ebb49a442db9acfa4cd752ed851d95488f954cc36e52",
            "eb505c55051ced3824e0e307092a1d8d6e0831adcf5ae8e91e60674f6b7acd88",
            "121d8d9ae5278e26bc4399923d25ccb9173e83748e9bad6df76314ba94369e");
    private static final List<String> mobHeads = Arrays.asList(
            "41645dfd77d09923107b3496e94eeb5c30329f97efc96ed76e226e98224",
            "895aeec6b842ada8669f846d65bc49762597824ab944f22f45bf3bbb941abe6c",
            "cd541541daaff50896cd258bdbdd4cf80c3ba816735726078bfe393927e57f1",
            "7a59bb0a7a32965b3d90d8eafa899d1835f424509eadd4e6b709ada50b9cf",
            "cdf74e323ed41436965f5c57ddf2815d5332fe999e68fbb9d6cf5c8bd4139f",
            "8b6a72138d69fbbd2fea3fa251cabd87152e4f1c97e5f986bf685571db3cc0",
            "b78ef2e4cf2c41a2d14bfde9caff10219f5b1bf5b35a49eb51c6467882cb5f0",
            "38957d5023c937c4c41aa2412d43410bda23cf79a9f6ab36b76fef2d7c429",
            "74e9c6e98582ffd8ff8feb3322cd1849c43fb16b158abb11ca7b42eda7743eb",
            "74ecc040785e54663e855ef0486da72154d69bb4b7424b7381ccf95b095a",
            "1c797482a14bfcb877257cb2cff1b6e6a8b8413336ffb4c29a6139278b436b",
            "e8b0a74335696b2bf4cf4780aca4a2835d401edb934669dcaec56fb97b670a7",
            "f31f9ccc6b3e32ecf13b8a11ac29cd33d18c95fc73db8a66c5d657ccb8be70",
            "5d6c6eda942f7f5f71c3161c7306f4aed307d82895f9d2b07ab4525718edc5",
            "1638469a599ceef7207537603248a9ab11ff591fd378bea4735b346a7fae893",
            "621668ef7cb79dd9c22ce3d1f3f4cb6e2559893b6df4a469514e667c16aa4",
            "01433be242366af126da434b8735df1eb5b3cb2cede39145974e9c483607bac",
            "d0bc61b9757a7b83e03cd2507a2157913c2cf016e7c096a4d6cf1fe1b8db",
            "5657cd5c2989ff97570fec4ddcdc6926a68a3393250c1be1f0b114a1db1",
            "707dab2cbebea539b64d5ad246f9ccc1fcda7aa94b88e59fc2829852f46071",
            "212b58c841b394863dbcc54de1c2ad2648af8f03e648988c1f9cef0bc20ee23c",
            "aec3ff563290b13ff3bcc36898af7eaa988b6cc18dc254147f58374afe9b21b9",
            "7bb4b288991efb8ca0743beccef31258b31d39f24951efb1c9c18a417ba48f9",
            "1433a4b73273a64c8ab2830b0fff777a61a488c92f60f83bfb3e421f428a44",
            "69d1d3113ec43ac2961dd59f28175fb4718873c6c448dfca8722317d67",
            "c25af966a326f9d98466a7bf8582ca4da6453de271b3bc9e59f57a99b63511c6",
            "382fc3f71b41769376a9e92fe3adbaac3772b999b219c9d6b4680ba9983e527",
            "da91dab8391af5fda54acd2c0b18fbd819b865e1a8f1d623813fa761e924540",
            "f4254838c33ea227ffca223dddaabfe0b0215f70da649e944477f44370ca6952",
            "f2ceb39dd4de24a7adfe291a3a0cfc7cf4f645de59b603fcfe06c6b5a06e26",
            "ea7bc0d76ac6de739e9e85727931ec823447397452059ff38141d37179edbe1",
            "e92089618435a0ef63e95ee95a92b83073f8c33fa77dc5365199bad33b6256");
    private static final List<String> presentHeads = Arrays.asList(
            "64abe81e6f4961e0f6bd82f2d4135b6b5fc845739e71cfe3b8943531d921e",
            "bdac9a51813abcb666ee2c2ca84cefcfb21e58b7b01ae0ea7e99d35f323a",
            "b7e6b74297020975cd7ecae547e5a0bb9feeac58974a94cba66d5ffe",
            "473ba546a62b32c515376dd7f3c92fb35a33524f323bbb2b5978e38c7e5f62a",
            "16234ae7d55903ea8bc34413cd52ded3b37c92eee5ae533fc5126a65461f11f",
            "14caafd233d3afd4b6f2132c63a694d012bad6d923316b3aa5c3768fee3339",
            "6cef9aa14e884773eac134a4ee8972063f466de678363cf7b1a21a85b7",
            "1b6730de7e5b941efc6e8cbaf5755f9421a20de871759682cd888cc4a81282",
            "d08ce7deba56b726a832b61115ca163361359c30434f7d5e3c3faa6fe4052",
            "a3e58ea7f3113caecd2b3a6f27af53b9cc9cfed7b043ba334b5168f1391d9",
            "75419fce506a495343a1d368a71d22413f08c6d67cb951d656cd03f80b4d3d3",
            "10c75a05b344ea043863974c180ba817aea68678cbea5e4ba395f74d4803d1d",
            "ac3821d4f61b17f82f0d7a8e5312608ff50ede29b1b4dc89847be9427d36",
            "59f0743576bba4a2622480548970b721543d2c457955e8dd5c4f9ddb6a56b95c",
            "ed97f4f44e796f79ca43097faa7b4fe91c445c76e5c26a5ad794f5e479837",
            "b73a2114136b8ee4926caa51785414036a2b76e4f1668cb89d99716c421",
            "78536a461684fc7a63b543c5df2348cd968bf5583591b1bbcc5f0db83166dc7",
            "5c712b1971c5f42eeff80551179220c08b8213eacbe6bc19d238c13f86e2c0",
            "7e8c242c051c894199c8510dad5aace96c2c4cf2e34f4e85a8e1845831ed5ed",
            "d505b7f8d5d3b912631c5c1ee6cc5b685a8ad4bae9a9552e8fdf7e711951f4",
            "278f1a858d66b9e7951f70acea2c19ab6c0af88ca5db516f1a1ff51f06b2c",
            "32f0489ca126a6e9f9afa59eb491b1853395b582b454fc2ad48027226252d121",
            "b5651a18f54714b0b8f7f011c018373b33fd1541ca6f1cfe7a6c97b65241f5",
            "f5612dc7b86d71afc1197301c15fd979e9f39e7b1f41d8f1ebdf8115576e2e");
    
    private static float randomVector() {
        return (float) -.1 + (float) (Math.random() * ((.1 - -.1)));
    }
    
    public static void startCustomFountain(Location loc, String id, String fountainName) {
        startCustomFountain(loc, id, particleManager.getCustomFountain(fountainName));
    }
    
    public static void startCustomFountain(Location loc, String id, CustomFountain fountain) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                for (ItemStack head : getRandomCustomHead(fountain.getHeads())) {
                    final Item headItem = loc.getWorld().dropItem(loc.clone().add(.5, .8, .5), head);
                    headItem.setVelocity(new Vector(randomVector(), .01, randomVector()));
                    particleManager.addFountainItem(headItem);

                    new FoliaRunnable(server.getRegionScheduler(), loc) {
                        @Override
                        public void run() {
                            particleManager.removeFountainItem(headItem);
                            headItem.remove();
                        }
                    }.runDelayed(plugin, 2 * 20);
                }
            }
        }.runAtFixedRate(plugin, 0, 3));
    }
    
    public static void startHalloween(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                ItemStack redstone = new ItemStack(Material.REDSTONE);
                ItemStack bone = new ItemStack(Material.BONE);
                ItemStack pumpkin = new ItemStack(Material.JACK_O_LANTERN);

                final Item fleshItem = loc.getWorld().dropItem(loc.clone().add(.5, 1, .5), flesh);

                fleshItem.customName(Component.text(new Random().nextInt(Integer.MAX_VALUE)));
                fleshItem.setCustomNameVisible(false);
                fleshItem.setCanMobPickup(false);
                fleshItem.setCanPlayerPickup(false);

                final Item redstoneItem = loc.getWorld().dropItem(loc.clone().add(.5, 1, .5), redstone);

                redstoneItem.customName(Component.text(new Random().nextInt(Integer.MAX_VALUE)));
                redstoneItem.setCustomNameVisible(false);
                redstoneItem.setCanMobPickup(false);
                redstoneItem.setCanPlayerPickup(false);

                final Item boneItem = loc.getWorld().dropItem(loc.clone().add(.5, 1, .5), bone);

                boneItem.customName(Component.text(new Random().nextInt(Integer.MAX_VALUE)));
                boneItem.setCustomNameVisible(false);
                boneItem.setCanMobPickup(false);
                boneItem.setCanPlayerPickup(false);

                final Item pumpkinItem = loc.getWorld().dropItem(loc.clone().add(.5, 1, .5), pumpkin);

                pumpkinItem.customName(Component.text(new Random().nextInt(Integer.MAX_VALUE)));
                pumpkinItem.setCustomNameVisible(false);
                pumpkinItem.setCanMobPickup(false);
                pumpkinItem.setCanPlayerPickup(false);

                fleshItem.setVelocity(new Vector(randomVector(), .3, randomVector()));
                particleManager.addFountainItem(fleshItem);
                redstoneItem.setVelocity(new Vector(randomVector(), .3, randomVector()));
                particleManager.addFountainItem(redstoneItem);
                boneItem.setVelocity(new Vector(randomVector(), .3, randomVector()));
                particleManager.addFountainItem(boneItem);
                pumpkinItem.setVelocity(new Vector(randomVector(), .3, randomVector()));
                particleManager.addFountainItem(pumpkinItem);

                new FoliaRunnable(server.getRegionScheduler(), loc) {
                    @Override
                    public void run() {
                        particleManager.removeFountainItem(fleshItem);
                        fleshItem.remove();
                        particleManager.removeFountainItem(redstoneItem);
                        redstoneItem.remove();
                        particleManager.removeFountainItem(boneItem);
                        boneItem.remove();
                        particleManager.removeFountainItem(pumpkinItem);
                        pumpkinItem.remove();
                    }
                }.runDelayed(plugin, 2 * 20);
            }
        }.runAtFixedRate(plugin, 0, 2));
    }
    
    public static void startGems(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                ItemStack emerald = new ItemStack(Material.EMERALD);
                ItemStack diamond = new ItemStack(Material.DIAMOND);
                ItemStack gold = new ItemStack(Material.GOLD_INGOT);

                final Item emeraldItem = loc.getWorld().dropItem(loc.clone().add(.5, 1, .5), emerald);

                emeraldItem.customName(Component.text(new Random().nextInt(Integer.MAX_VALUE)));
                emeraldItem.setCustomNameVisible(false);
                emeraldItem.setCanMobPickup(false);
                emeraldItem.setCanPlayerPickup(false);

                final Item diamondItem = loc.getWorld().dropItem(loc.clone().add(.5, 1, .5), diamond);

                diamondItem.customName(Component.text(new Random().nextInt(Integer.MAX_VALUE)));
                diamondItem.setCustomNameVisible(false);
                diamondItem.setCanMobPickup(false);
                diamondItem.setCanPlayerPickup(false);

                final Item goldItem = loc.getWorld().dropItem(loc.clone().add(.5, 1, .5), gold);

                goldItem.customName(Component.text(new Random().nextInt(Integer.MAX_VALUE)));
                goldItem.setCustomNameVisible(false);
                goldItem.setCanMobPickup(false);
                goldItem.setCanPlayerPickup(false);

                emeraldItem.setVelocity(new Vector(randomVector(), .3, randomVector()));
                particleManager.addFountainItem(emeraldItem);
                diamondItem.setVelocity(new Vector(randomVector(), .3, randomVector()));
                particleManager.addFountainItem(diamondItem);
                goldItem.setVelocity(new Vector(randomVector(), .3, randomVector()));
                particleManager.addFountainItem(goldItem);

                new FoliaRunnable(server.getRegionScheduler(), loc) {
                    @Override
                    public void run() {
                        particleManager.removeFountainItem(emeraldItem);
                        emeraldItem.remove();
                        particleManager.removeFountainItem(diamondItem);
                        diamondItem.remove();
                        particleManager.removeFountainItem(goldItem);
                        goldItem.remove();
                    }
                }.runDelayed(plugin, 2 * 20);
            }
        }.runAtFixedRate(plugin, 0, 2));
    }
    
    public static void startHeads(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                int radius = 10;
                for (Entity entity : getNearbyEntities(loc.clone(), radius, radius, radius)) {
                    if (entity instanceof Player player) {
                        final Item head = loc.getWorld().dropItem(loc.clone().add(.5, .8, .5), HeadDatabaseHook.getPlayerHead(player.getName()));
                        head.setVelocity(new Vector(randomVector(), .01, randomVector()));
                        particleManager.addFountainItem(head);

                        new FoliaRunnable(server.getRegionScheduler(), loc) {
                            @Override
                            public void run() {
                                particleManager.removeFountainItem(head);
                                head.remove();
                            }
                        }.runDelayed(plugin, 2 * 20);
                    }
                }
            }
        }.runAtFixedRate(plugin, 0, 3));
    }
    
    public static void startPresents(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                for (String head : getRandomHeads(presentHeads)) {
                    final Item headItem = loc.getWorld().dropItem(loc.clone().add(.5, .8, .5), HeadDatabaseHook.getPlayerHead(head));
                    headItem.setVelocity(new Vector(randomVector(), .01, randomVector()));
                    particleManager.addFountainItem(headItem);

                    new FoliaRunnable(server.getRegionScheduler(), loc) {
                        @Override
                        public void run() {
                            particleManager.removeFountainItem(headItem);
                            headItem.remove();
                        }
                    }.runDelayed(plugin, 2 * 20);
                }
            }
        }.runAtFixedRate(plugin, 0, 3));
    }
    
    public static void startMobs(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                for (String head : getRandomHeads(mobHeads)) {
                    final Item headItem = loc.getWorld().dropItem(loc.clone().add(.5, .8, .5), HeadDatabaseHook.getPlayerHead(head));
                    headItem.setVelocity(new Vector(randomVector(), .01, randomVector()));
                    particleManager.addFountainItem(headItem);

                    new FoliaRunnable(server.getRegionScheduler(), loc) {
                        @Override
                        public void run() {
                            particleManager.removeFountainItem(headItem);
                            headItem.remove();
                        }
                    }.runDelayed(plugin, 2 * 20);
                }
            }
        }.runAtFixedRate(plugin, 0, 3));
    }
    
    public static void startFood(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                for (String head : getRandomHeads(foodHeads)) {
                    final Item headItem = loc.getWorld().dropItem(loc.clone().add(.5, .8, .5), HeadDatabaseHook.getPlayerHead(head));
                    headItem.setVelocity(new Vector(randomVector(), .01, randomVector()));
                    particleManager.addFountainItem(headItem);

                    new FoliaRunnable(server.getRegionScheduler(), loc) {
                        @Override
                        public void run() {
                            particleManager.removeFountainItem(headItem);
                            headItem.remove();
                        }
                    }.runDelayed(plugin, 2 * 20);
                }
            }
        }.runAtFixedRate(plugin, 0, 3));
    }
    
    public static void startPokemon(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                for (String head : getRandomHeads(pokemonHeads)) {
                    final Item headItem = loc.getWorld().dropItem(loc.clone().add(.5, .8, .5), HeadDatabaseHook.getPlayerHead(head));
                    headItem.setVelocity(new Vector(randomVector(), .01, randomVector()));
                    particleManager.addFountainItem(headItem);

                    new FoliaRunnable(server.getRegionScheduler(), loc) {
                        @Override
                        public void run() {
                            particleManager.removeFountainItem(headItem);
                            headItem.remove();
                        }
                    }.runDelayed(plugin, 2 * 20);
                }
            }
        }.runAtFixedRate(plugin, 0, 3));
    }
    
    public static void startMario(final Location loc, String id) {
        particleManager.getParticleControl().getLocations().put(id, new FoliaRunnable(server.getRegionScheduler(), loc) {
            @Override
            public void run() {
                for (String head : getRandomHeads(marioHeads)) {
                    final Item headItem = loc.getWorld().dropItem(loc.clone().add(.5, .8, .5), HeadDatabaseHook.getPlayerHead(head));
                    headItem.setVelocity(new Vector(randomVector(), .01, randomVector()));

                    new FoliaRunnable(server.getRegionScheduler(), loc) {
                        @Override
                        public void run() {
                            particleManager.removeFountainItem(headItem);
                            headItem.remove();
                        }
                    }.runDelayed(plugin, 2 * 20);
                }
            }
        }.runAtFixedRate(plugin, 0, 3));
    }
    
    private static List<String> getRandomHeads(List<String> headList) {
        List<String> pickedHeads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int p = random.nextInt(headList.size());
            pickedHeads.add(headList.get(p));
        }
        return pickedHeads;
    }
    
    private static List<ItemStack> getRandomCustomHead(List<ItemStack> headList) {
        List<ItemStack> pickedHeads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int p = random.nextInt(headList.size());
            pickedHeads.add(headList.get(p));
        }
        return pickedHeads;
    }
    
    @SuppressWarnings("deprecation")
    private static List<Entity> getNearbyEntities(Location loc, double x, double y, double z) {
        if (loc == null || loc.getWorld() == null) return new ArrayList<>();
        FallingBlock ent = loc.getWorld().spawnFallingBlock(loc.subtract(0, 1, 0), Material.TRIPWIRE, (byte) 0);
        List<Entity> out = ent.getNearbyEntities(x, y, z);
        ent.remove();
        return out;
    }
    
    @EventHandler
    public void onHopperPickUp(InventoryPickupItemEvent e) {
        if (particleManager.getFountainItem().contains(e.getItem())) {
            e.setCancelled(true);
        }
    }
}