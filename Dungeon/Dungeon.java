import java.util.*;
import java.io.*;
//import javax.swing.*;

public class Dungeon {
    //initialize Variables
    public static int level = 1; //highest weapon tier that can be generated. Adds onto enemy damage
	public static int subLevel = 0;
    static String mode = "realistic";
	static String battleLast;
	static String weaponType;
    static double healthMult = 1.0; //enemy health = base x this
    static int potionHeal = 60; //potion regen amount
    static int potionMana = 60;
    static int potionBCost = 10;//base costs for potions
	static int hPotionCost = 10;//current cost
	static int mPotionCost = 10;
	static double mPotionMult = 1.0;//cost multiplier
	static double hPotionMult = 1.0;//cost multiplier
    static int weaponCost;
	static int reloadCost;
    static int enId;
    static Weapon shopW;
	static boolean findWeapon = false;
    static boolean isChest = false;
    static boolean changeW = true;
    static boolean wGen = false;
    static boolean shop = false;
    static boolean fast = false;
	static boolean usedMoney = false;
	static boolean usedLevel = false;
    
    //initialise Classes
    static Player pl = new Player();
	
	//used to call general class functions eg: melee.enHit()
	static Weapon melee         = new Melee("", 0, 0); 
	static Weapon ranged         = new Ranged("", 0, 0);
	static Weapon magic         = new Magic("", 0, 0);
    //static DungeonT dt = new DungeonT();
    
    //Melee weapons - speed determines dodge chance
    static Weapon dagger         = new Melee("Dagger", 5, 7);            
    static Weapon sword          = new Melee("Sword", 7, 6);             
    static Weapon mace           = new Melee("Mace", 12, 5);                
    static Weapon axe            = new Melee("Axe", 8, 5);             
    static Weapon warHammer      = new Melee("War Hammer", 16, 4);        
    static Weapon quarterstaff   = new Melee("Quarterstaff", 10, 8);      
    static Weapon greatsword     = new Melee("Greatsword", 11, 6);      
    static Weapon shank          = new Melee("Shank", 4, 8);                
    static Weapon spear          = new Melee("Spear", 8, 6);               
    static Weapon wristBlades    = new Melee("Wrist Blades", 7, 5);       
    static Weapon battleAxe       = new Melee("Battle Axe", 16, 4);          
    static Weapon morningstar    = new Melee("Morningstar", 14, 6);       
    static Weapon pike           = new Melee("Pike", 10, 8);              
    static Weapon cutlass        = new Melee("Cutlass", 6, 7);           
    static Weapon flail          = new Melee("Flail", 9, 3);               
    static Weapon club           = new Melee("Club", 7, 4);      
    static Weapon warScythe      = new Melee("War Scythe", 17 ,1);        
            
    //Ranged weapons - ^ same
    static Weapon shortbow       = new Ranged("Shortbow", 5, 7);         
    static Weapon longbow        = new Ranged("Longbow", 7, 5);          
    static Weapon shurikan       = new Ranged("Shurikan", 3, 9);         
    static Weapon crossbow       = new Ranged("Crossbow", 8, 4);
    static Weapon magicStaff     = new Ranged("Magic Staff", 14, 15);
    static Weapon sling          = new Ranged("Sling", 4, 8);
    static Weapon tomahawk       = new Ranged("Tomahawk", 6, 6);
    static Weapon dartGun        = new Ranged("Dart Gun", 3, 9);
    static Weapon compoundBow    = new Ranged("Compound Bow", 13, 3);
    static Weapon rCrossbow      = new Ranged("Repeating Crossbow", 6, 10);
    static Weapon javelin        = new Ranged("Javelin", 9, 7);
           
    //Magic weapons - speed increases chance to hit on top of weapon speed 
    static Weapon flame          = new Magic("Fireball", 12, 4);            
    static Weapon lightning      = new Magic("Lightning Bolt", 10, 10);    
    static Weapon frost          = new Magic("Ice Beam", 14, 2);          
    static Weapon sapping        = new Magic("Drain Speed", 10, 5); //decrease en speed  
    static Weapon aura           = new Magic("Defensive Aura", 10, 5); //decrease en damage   
    static Weapon speed          = new Magic("Swiftness", 10, 10); //increase pl speed 
    static Weapon shift          = new Magic("Dimensional Shift", 10, 10); //increase pl dodge
    static Weapon fireWall       = new Magic("Wall Of Fire", 10, 10); //damage melee attackers 
    
    //Enemies - Kept in Weapon class in case you 
    //want to 'wield' (ie summon) an ally monster
    static Weapon skeleton         = new Melee("Skeleton", 8, 7);           
    static Weapon spider           = new Melee("Spider", 6, 8);             
    static Weapon troll            = new Melee("Troll", 11, 2);              
    static Weapon snake            = new Melee("Snake", 4, 8);              
    static Weapon necromancer      = new Magic("Necromancer", 10, 5);        
    static Weapon wizard           = new Magic("Wizard", 11, 5);             
    static Weapon skeletonArcher   = new Ranged("Skeleton Archer", 7, 6);   
    static Weapon goblin           = new Melee("Goblin", 6, 7);             
    static Weapon outlaw           = new Melee("outlaw", 8, 6);             
    static Weapon caveRat          = new Melee("Cave Rat", 4, 8);           
    static Weapon wraith           = new Melee("Wraith", 10, 5);             
    static Weapon zealot          = new Melee("Zealot", 9, 4);             
    static Weapon demon            = new Magic("Demon", 11, 5);              
    static Weapon dragon           = new Melee("Dragon", 12, 2);             
    static Weapon orc              = new Melee("Orc", 9, 4);                
    static Weapon vampire          = new Magic("Vampire", 9, 5);            
    static Weapon leviathan        = new Melee("Leviathan", 11, 3);          
    static Weapon pixie            = new Magic("Pixie", 4, 8);              
    static Weapon harpy            = new Melee("Harpy", 5, 7);              
    static Weapon fallenHero       = new Melee("Fallen Hero", 9, 5);        
    static Weapon guardian         = new Melee("Guardian", 11, 2);           
    static Weapon carnPlant        = new Melee("Carnivorous Plant", 7, 4);  
    static Weapon giant            = new Melee("Giant", 9, 2);              
    static Weapon looter           = new Melee("Looter", 6, 6);             
    static Weapon wyvern           = new Melee("Wyvern", 10, 3);               
    static Weapon cursedSoul       = new Melee("Cursed Soul", 8, 4);        
    static Weapon fElemental       = new Ranged("Fire Elemental", 9, 5);    
    static Weapon wElemental       = new Ranged("Water Elemental", 9, 5);   
    static Weapon eElemental       = new Ranged("Earth Elemental", 9, 5);   
    static Weapon aElemental       = new Ranged("Air Elemental", 9, 5);     
    static Weapon basilisk         = new Melee("Basilisk", 11, 3);           
    static Weapon golem            = new Melee("Rock Golem", 10, 2);
    static Weapon manticore        = new Melee("Manticore", 5, 5);
    static Weapon draugr           = new Melee("Draugr", 5, 5);
    static Weapon legion           = new Melee("Legion", 5, 5);
    static Weapon gtmichaels       = new Melee("GtMichaels", 5, 5);
    static Weapon arvin            = new Melee("Arvin", 1, 1);
    static Weapon griffin          = new Melee("Griffin", 5, 5);
    static Weapon mimic            = new Melee("Mimic", 9, 4);
	
    
    public static void main(String[] args) {
        //set up beginning of game
        Scanner s = new Scanner(System.in);
        int weaponM = pl.getWeaponM(); //gives Player melee weapon info from the get go
        WeaponStatsT1(weaponM);
		changeW = true;
		int weaponR = pl.getWeaponR(); //gives Player ranged weapon info from the get go
        WeaponStatsT1(weaponR);
        String input = "";
        while (!input.equalsIgnoreCase("start")) {
            //option select
            System.out.println("");
            System.out.println("       Do you want to enter The Dungeon?");
            System.out.println("  ===========================================");
            System.out.println("  [Start] [Help] [Controls] [Tutorial] [Quit]");
            System.out.print("  ");
            input = s.nextLine();
            if (input.equalsIgnoreCase("start") || input.equalsIgnoreCase("s")) {
                LevelChain(null);//level method
            }
            else if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("q")) {
                System.exit(1);
            }
            else if (input.equalsIgnoreCase("controls") || input.equalsIgnoreCase("c")) {
                Controls(null);
            }
            else if (input.equalsIgnoreCase("tutorial") || input.equalsIgnoreCase("t")) {
                try {
                    FileWriter fw = new FileWriter("Save.txt");
                    fw.write(mode);    
                    fw.close();    
                } 
                catch(Exception e){
                    System.out.println(e);
                }    
                System.out.println("");
                System.out.println("  Mode changed to Tutorial");
                
                //dt.main(null);
                System.exit(1);
            }
            else {
                Help(null);
            }
        }
        
    }
    
    private static void LevelChain(String[] args) {
        System.out.println("");
        System.out.println("  You descend the stairs...");
                
        Delay(null); //make game wait for user
        Random r = new Random();
        int vary = r.nextInt(3)+4; //variation on number of levels (btwn 4 - 6)
        int roomArr[] = new int[vary]; //create array of vary length
        for (int i=0; i<vary; i++) {
            //call RoomId and grab Id
            int checkRoomId = -1;
            int roomId = RoomId(checkRoomId);
            boolean isDup = isDuplicate(roomArr, roomId); //check for duplicates
            if (isDup == true) {
                i--;
            }
            else {
                roomArr[i] = roomId; //if no dupes, add Id to array and execute room
				RoomGen(roomId);
				subLevel++;
            }
        }
        
        NextLevel(null);
    }
    
    private static boolean isDuplicate(int roomArr[], int Id) {//duplicate room check function
        for (int i=0; i<roomArr.length; i++) {
            if (Id == roomArr[i])
            {
                return true;
            } 
        }
        return false;
    }
    
    private static void Help(String[] args) {
        System.out.println("");
        System.out.println("  When you need to make a choice, your options appear in");
        System.out.println("  square brackets under a double line, like this:");
        System.out.println("");
		System.out.println("   What will you do?");
        System.out.println("  ===================");
        System.out.println("  [Option1] [Option2]");
        System.out.println("");
        System.out.println("  The game will then pause and wait for input. To choose an");
        System.out.println("  option, just type the first letter 'o' or the whole option");
        System.out.println("  name 'option1'. Then press enter and the option is selected.");
        System.out.println("  when the game pauses without this input prompt, simply press");
        System.out.println("  enter and the game will continue.");
        Delay(null);
    }
    
    private static void Controls(String[] args) {
        System.out.println("");
        System.out.println("  KEYBINDINGS            FUNCTION");
        System.out.println("  'quit' or 'q'..........save and quit the game");
        System.out.println("  'help' or 'h' or '?'...bring up help menu");
        System.out.println("  'examine' or 'e'.......examine enemy");
        System.out.println("");
        Delay(null);
    }
    
    private static void NextLevel(String[] args) {
        //This method will bump up enemy stats, loot spawns etc
        level++;
        healthMult += 0.2;
        System.out.println("  You find a staircase leading deeper into the dungeon");
        LevelChain(null); //then restart LevelChain
    }
    
    public static void Delay(String[] args) {//wait for user function
        if (fast == true) {
            System.out.println("");
            return;
        }
        Scanner s = new Scanner(System.in);
        String delay = s.nextLine();
        
        if (delay.equalsIgnoreCase("?") || delay.equalsIgnoreCase("help") || delay.equalsIgnoreCase("h")) {
            Help(null);
        }
        else if (delay.equalsIgnoreCase("quit")|| delay.equalsIgnoreCase("q")) {
            System.exit(1);
        }
        else if (delay.equalsIgnoreCase("examine") || delay.equalsIgnoreCase("e")) {
            System.out.println("  enId in Delay() is "+enId);
            Examine(enId);
        }
        else if (delay.equalsIgnoreCase("fast")) {
            fast = !fast;
        }
        else if (delay.equalsIgnoreCase("money")) {
            pl.setCash(999999999);
			usedMoney = true;
        }
        else if (delay.equalsIgnoreCase("level")) {
            level = 3;
			usedLevel = true;
        }
		else if (delay.equalsIgnoreCase("debug")) {
			System.out.println("");
			System.out.println("---- DEBUG STATS ----");
			System.out.println("");
			System.out.println("  level: "+level);
			System.out.println("  Enemy Health Multiplier: "+healthMult);
			System.out.println("  Last enemy Id: "+enId);
			System.out.println("");
			System.out.println("  Current Boolean states");
			System.out.println("  Weapon found: "+findWeapon);
			System.out.println("  getting loot from chest: "+isChest);
			System.out.println("  force weapon change: "+changeW);
			System.out.println("  Generate weapon when possible: "+wGen);
			System.out.println("  Currently in Shop: "+shop);
			System.out.println("  Fast mode on: "+fast);
			System.out.println("  Artificially advanced money: "+usedMoney);
			System.out.println("  Artificially advanced level: "+usedLevel);
			System.out.println("");
			System.out.println("  Player Values");
			System.out.println("  Coins: "+pl.getCash());
			System.out.println("  Health: "+pl.getHealth());
			System.out.println("  Score: "+pl.getScore());
			System.out.println("  Mana: "+pl.getMana());
			System.out.println("  Health Potions: "+pl.getHPotions());
			System.out.println("  Mana Potions: "+pl.getMPotions());
			System.out.println("  Melee Weapon Id: "+pl.getWeaponM());
			System.out.println("  Melee Weapon name: "+pl.getNameM());
			System.out.println("  Melee Weapon quality: "+pl.getQNameM());
			System.out.println("  Melee Weapon damage: "+pl.getDamageM());
			System.out.println("  Melee Weapon speed: "+pl.getSpeedM());
			System.out.println("  Ranged Weapon Id: "+pl.getWeaponR());
			System.out.println("  Melee Weapon name: "+pl.getNameR());
			System.out.println("  Ranged Weapon quality: "+pl.getQNameR());
			System.out.println("  Ranged Weapon damage: "+pl.getDamageR());
			System.out.println("  Ranged Weapon ammo: "+pl.getAmmo());
			System.out.println("  Ranged weapon max ammo: "+pl.getMaxAmmo());
			System.out.println("");
			System.out.println("---- DEBUG STATS ----");
			System.out.println("");
			
			
		}
    }

    private static int RoomId(int roomId) { //room Id list
        Random r = new Random();
        roomId = r.nextInt(51)+1; //update with new room cases
        //change max random number to change shop spawn chance 
        //(E.g. 15 cases (rooms), max ran 20 = 1:4 spawn ratio)
        if (roomId > 25) {
            roomId = 26; //give shops one Id
        }
        return roomId; //pass RoomId back to Caller
    }
    
    private static void Battle(Weapon e, int h) {
        Enemy en = new Enemy();
        e.newWeapon();
        en.setHealth((int) Math.round(h*healthMult)); //set enemy health
		String input = "";
		String subInput = battleLast;
		Delay(null);
		if (enId == 32) {
			System.out.println("  It's a Mimic!");
		} else {
			System.out.println("  A " + e.getName() + " appears!");
		}
        
        Delay(null);
        
        while (en.getHealth() > 0) {
            Scanner s = new Scanner(System.in);
			boolean last = false;
            System.out.println("       What will you do?");
            System.out.println("  ===========================");
            System.out.println("  [Weapon]  [Potions]  [Last]");
			System.out.print("  ");
            input = s.nextLine();
			if (input.equalsIgnoreCase("last") || input.equalsIgnoreCase("l")) {
				last = true;
				//select last choice to make battles less tedious
			}
            if (input.equalsIgnoreCase("weapon") || input.equalsIgnoreCase("w") || last == true) {
				if (last != true) {
					System.out.println("");
					System.out.println("       What weapon do you use?");
					System.out.println("  ==================================");
					System.out.println("  [Melee] [Ranged("+pl.getAmmo()+")] [Spell] [Back]");
					System.out.print("  ");
					subInput = s.nextLine();
				}
				if (subInput.equalsIgnoreCase("melee") || subInput.equalsIgnoreCase("m")) {
					enDodgeChance(e, en);
					if (en.getDead()) {
						pl.addScore(100+h); //+100 for winning, + damage dealt
						System.out.println("");
						GetLoot(null); //get money
						System.out.println("  You enter the next room");
						Delay(null);
						return;
					}
					Delay(null);
					plDodgeChance(e);
				}
				else if (subInput.equalsIgnoreCase("ranged") || subInput.equalsIgnoreCase("r")) {
					if (pl.getAmmo() != 0) {
						ranged.EnHit(e, en, pl);
						if (en.getDead()) {
							pl.addScore(100+h); //+100 for winning, + damage dealt
							System.out.println("");
							GetLoot(null); //get money
							System.out.println("  You enter the next room");
							Delay(null);
							return;
						}
						Delay(null);
						plDodgeChance(e);
					} else {
						System.out.println("");
						System.out.println("  You don't have any more ammunition!");
					}
				}
				else if (subInput.equalsIgnoreCase("spell") || subInput.equalsIgnoreCase("s")) {
					System.out.println("     What spell do you use?");
					System.out.println("  ===========================");
					if (pl.getSpell("Wall of Fire")) {
						
					}
					System.out.print("  ");
					input = s.nextLine();
					
					magic.EnHit(e, en, pl);
					if (en.getDead()) {
						pl.addScore(100+h); //+100 for winning, + damage dealt
						System.out.println("");
						GetLoot(null); //get money
						System.out.println("  You enter the next room");
						Delay(null);
						return;
					}
					Delay(null);
					plDodgeChance(e);
				}
				else if (subInput.equalsIgnoreCase("back") || subInput.equalsIgnoreCase("b")) {
					System.out.println("");
					continue;
				}
            }
			else if (input.equalsIgnoreCase("potions") || input.equalsIgnoreCase("p") || last == true) {
				if (last != true) {
					System.out.println("");
					System.out.println("    What potion do you use?");
					System.out.println("  ============================");
					System.out.println("  [Health("+pl.getHPotions()+")] [Mana("+pl.getMPotions()+")] [Back]");
					System.out.print("  ");
					subInput = s.nextLine();
				}
				if (subInput.equalsIgnoreCase("health") || subInput.equalsIgnoreCase("h")) {
					if (pl.getHPotions() > 0) {
						System.out.println("  You feel your body being repaired");
						pl.setHealth(potionHeal);
						pl.setHPotions(-1);
						System.out.println("  You have "+pl.getHPotions()+" health potions");
						Delay(null);
						plDodgeChance(e);
					} else {
						System.out.println("");
						System.out.println("  You don't have any more health potions!");
					}
                
				}
				else if (subInput.equalsIgnoreCase("mana") || subInput.equalsIgnoreCase("m")) {
					if (pl.getMPotions() > 0) {
						System.out.println("  Your magik is restored");
						pl.setMana(potionMana);
						pl.setMPotions(-1);
						System.out.println("  You have "+pl.getHPotions()+" health potions");
						Delay(null);
						plDodgeChance(e);
					} else {
						System.out.println("");
						System.out.println("  You don't have any more mana potions!");
					}
				} 
				else if (subInput.equalsIgnoreCase("back") || subInput.equalsIgnoreCase("b")) {
					System.out.println("");
					continue;
				} else {
					System.out.println("");
					System.out.println("  Not a valid option. Enter '?' for help");
				}
			} else {
				System.out.println("");
				System.out.println("  Not a valid option. Enter '?' for help");
			}
            battleLast = subInput;
			last = false;
			Delay(null);
        }
    }
    
    private static void enDodgeChance(Weapon e, Enemy en) {
        //dodge chance is speed * X% chance, eg 6x3 = 18% chance
        //to dodge attack with max 10 * X% (10x4) chance to dodge
        //to dodge score must be lower than target.
        Random r = new Random();
        int target = e.getSpeed() * 4;
        int score = r.nextInt(100)+1;
        
        if (score > target) {
            melee.EnHit(e, en, pl);
        } else {
            System.out.println("");
            System.out.println("  The "+e.getName()+" dodges your attack!");
        }
    }
    
    private static void plDodgeChance(Weapon e) {
        Random r = new Random();
        int target = e.getSpeed() * 4;
        int score = r.nextInt(100)+1;
        
        if (score > target) {
            melee.PlHit(e, pl);
        } else {
            System.out.println("  You dodge the "+e.getName()+"'s attack!");
        }
    }
    
    private static void Shop(String[] args) {
		shop = true;
        Scanner s = new Scanner(System.in);
        String input = "";
        String subInput = "";
        wGen = true;
        WeaponTier(null);
        shopW.newWeapon();
        wGen = false;
        
        
        while (!input.equalsIgnoreCase("leave") && !input.equalsIgnoreCase("l")) {
            System.out.println("   What do you want to buy?");
            System.out.println("  ===========================");
            System.out.println("  [Potions] [Weapons] [Leave]");
            System.out.print("  ");
            input = s.nextLine();
            System.out.println("");
            
            if (input.equalsIgnoreCase("potions") || input.equalsIgnoreCase("p")) {
                System.out.println("      What do you want to buy?");
                System.out.println("  ================================");
                System.out.println("  [Health:"+hPotionCost+"]  [Mana:"+mPotionCost+"]  [Back]");
                System.out.println("  Balance: "+pl.getCash());
                System.out.print("  ");
                subInput = s.nextLine();
                System.out.println("");
                
                if (subInput.equalsIgnoreCase("health") || subInput.equalsIgnoreCase("h")) {
                    if (pl.getCash() > hPotionCost) {
                        pl.setHPotions(1);
                        pl.setCash(-hPotionCost);
                        hPotionCost += (int) Math.round((potionBCost * hPotionMult) * level);
						
                        System.out.println("  You purchased a health potion");
                        System.out.println("  You have "+pl.getHPotions()+" health potions");
                    } else {
                        System.out.println("  You don't have enough money!");
                    }
                }
                else if (subInput.equalsIgnoreCase("mana") || subInput.equalsIgnoreCase("m")) {
                    if (pl.getCash() > mPotionCost) {
                        pl.setMPotions(1);
                        pl.setCash(-mPotionCost);
                        mPotionCost += (int) Math.round((potionBCost * mPotionMult) * level);
                        System.out.println("  You purchased a health potion");
                        System.out.println("  You have "+pl.getMPotions()+" health potions");
                    } else {
                        System.out.println("  You don't have enough money!");
                    }
                }
                else if (subInput.equalsIgnoreCase("back") || subInput.equalsIgnoreCase("b")) {
                    continue;
                } else {
                        System.out.println("  Not a valid option. Enter '?' for help");
                } 
            }
            else if (input.equalsIgnoreCase("weapons") || input.equalsIgnoreCase("w")) {
				System.out.println("  What do You want to buy?");
				System.out.println("  ========================");
				System.out.println("  [Weapon] [Reload] [Back]");
				System.out.print("  ");
				subInput = s.nextLine();
				System.out.println("");
				
                if (subInput.equalsIgnoreCase("weapon") || subInput.equalsIgnoreCase("w")) {
					GetStats(shopW);
				}
				else if (subInput.equalsIgnoreCase("reload") || subInput.equalsIgnoreCase("r")) {
					if (pl.getAmmoDiff() == 0) {
						System.out.println("  Purchase "+pl.getAmmoDiff()+" ammo for "+reloadCost+" coins?");
						System.out.println("  ==============================");
						System.out.println("         [Yes]       [No]");
						System.out.print("  ");
						subInput = s.nextLine();
						System.out.println("");
					} else {
						System.out.println("  You can't hold any more ammo!");
						continue;
					}
					if (subInput.equalsIgnoreCase("yes") || subInput.equalsIgnoreCase("y")) {
						if (pl.getCash() > reloadCost) {
							System.out.println("  You purchase a reload");
							pl.setCash(-reloadCost);
							pl.setNewAmmo(pl.getMaxAmmo());
						} else {
							System.out.println("  You don't have enough money!");
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
            }
            else if (input.equalsIgnoreCase("leave") || input.equalsIgnoreCase("l")) {
                System.out.println("  The shopkeeper wishes you luck");
                
            } else {
                System.out.println("  Not a valid option. Enter '?' for help");
            }
            Delay(null);
        }
		shop = false;
    }  
    
    private static void GetLoot(String[] args) {
        Random r = new Random();
		int wChance = r.nextInt(10)+1;
		if (pl.getCash() > 99999999) { //debug always make weapons appear
			wChance = 1;
		}
        if (wChance == 1) {
			findWeapon = true;
            System.out.println("  You find a weapon!");
            Delay(null);
            WeaponTier(null);
			findWeapon= false;
        } else {
            int loot = (r.nextInt(11)+5) * level;
            if (isChest == true) {
                Delay(null);
                System.out.println("  you open the chest to see what's in it");
                pl.setCash(loot * 2);
                isChest = false;
                Delay(null);
            }
			System.out.println("");
            System.out.println("  You find "+loot+" coins");
            Delay(null);
            pl.setCash(loot);
        }
    }
    
    private static void SetStats(Weapon w) {
        if(w.getType().equalsIgnoreCase("melee")) {
			pl.setDamageM(w.getDamage()); 
			pl.setSpeedM(w.getSpeed());
			pl.setNameM(w.getName());
			w.SendQualityNameM();
		} 
		else if(w.getType().equalsIgnoreCase("ranged")) {
			pl.setDamageR(w.getDamage()); 
			pl.setNewAmmo(w.getSpeed());
			pl.setMaxAmmo(w.getSpeed());
			pl.setNameR(w.getName());
			w.SendQualityNameR();
			reloadCost = weaponCost/2;
			if (reloadCost < 10) {
				reloadCost = 10;
			}
		}
    }
    
    private static void GetStats(Weapon w) {
		if (findWeapon == true) {
			w.newWeapon();
		}
        Scanner s = new Scanner(System.in);
        String input = "";
		int localPlDamage = 0;
		int localPlSpeed = 0;
		String localPlName = "";
		String localPlQName = "";
		int localWDamage = w.getDamage();
		int localWSpeed = w.getSpeed();
		String localWName = w.getName();
		String localWQName = w.qualityN;
		String speedType = "  Speed: ";
		String showAmmo = "";
		
        if (changeW == true) { //automatic weapon change
            w.newWeapon();
            SetStats(w);
			changeW = false;
			return;
        } 
        if (wGen == true) {
            shopW = w;
			return;
        }
		if (w.getType().equalsIgnoreCase("ranged") && pl.getWeaponR() != -1) {
			//change ranged weapon
			localPlDamage = pl.getDamageR(); 
			localPlSpeed = pl.getMaxAmmo();
			localPlName = pl.getNameR();
			localPlQName = pl.getQNameR();
			speedType = "  Max Ammo: ";
			showAmmo = "  Current Ammo: ";
		}  
		if (w.getType().equalsIgnoreCase("melee")) {
			//change melee weapon
			localPlDamage = pl.getDamageM(); 
			localPlSpeed = pl.getSpeedM();
			localPlName = pl.getNameM();
			localPlQName = pl.getQNameM();
			speedType = "  Speed: ";
			showAmmo = "";
		}
        if (shop == true) {
            System.out.println("");
            System.out.println("  Current Weapon:");
            System.out.println("  Name: "+localPlName);
            System.out.println("  Quality: "+localPlQName);
            System.out.println("  Damage: "+localPlDamage); 
            System.out.println(   speedType+localPlSpeed);
			System.out.println(   showAmmo+pl.getAmmo());
            System.out.println("");
            System.out.println("  New Weapon:");
            System.out.println("  Name: "+localWName);
            System.out.println("  Quality: "+localWQName);
            System.out.println("  Damage: "+localWDamage); 
            System.out.println(   speedType+localWSpeed);
            System.out.println("");
            System.out.println("  Cost: "+weaponCost);
            System.out.println("  Balance: "+pl.getCash());
            System.out.println("");
            System.out.println("  Do you want to buy this weapon?");
            System.out.println("  ===============================");
            System.out.println("        [Yes]        [No]");
            System.out.print("  ");
            input = s.nextLine();
            System.out.println("");
            
            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                if (pl.getCash() > weaponCost) {
                    System.out.println("  You purchase the weapon");
                    pl.setCash(-weaponCost);
                    SetStats(w);
                } else {
                    System.out.println("  You don't have enough money!");
                }
            }
            else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
                System.out.println("  You keep your current weapon");
            } else {
                System.out.println("  Not a valid option. Enter '?' for help");
            }
            System.out.println("");
        } 
        if (findWeapon == true) { //if you find the weapon
            while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("n")) {
                System.out.println("");
                System.out.println("  Current Weapon:");
                System.out.println("  Name: "+localPlName);
                System.out.println("  Quality: "+localPlQName);
                System.out.println("  Damage: "+localPlDamage); 
                System.out.println(   speedType+localPlSpeed);
				System.out.println(   showAmmo+pl.getAmmo());
                System.out.println("");
                System.out.println("  New Weapon:");
                System.out.println("  Name: "+localWName);
                System.out.println("  Quality: "+localWQName);
                System.out.println("  Damage: "+localWDamage); 
                System.out.println(   speedType+localWSpeed);
                System.out.println("");
                System.out.println("  Do you want to change your weapon?");
                System.out.println("  ==================================");
                System.out.println("           [Yes]        [No]");    
                System.out.print("  ");
                input = s.nextLine();
                System.out.println("");
                
                if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
                    System.out.println("  You change your weapon");
                    SetStats(w);
                }
                else if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
                    System.out.println("  You keep your current weapon");
                } else {
                    System.out.println("  Not a valid option. Enter '?' for help");
                }
                System.out.println("");
            }
        }
    }
    
    private static void WeaponTier(String[] args) {
        //only selects weapon up to + including current level
        Random t = new Random();
        Random q = new Random();
        int tier = 5;
        while (tier > 3) {
            tier = t.nextInt(level)+1;
        }
        int w;
        switch (tier) {
            case 1: w = q.nextInt(12)+1;
                    WeaponStatsT1(w); break;
            case 2: w = q.nextInt(9)+1;
                    WeaponStatsT2(w); break;
            case 3: w = q.nextInt(7)+1;
                    WeaponStatsT3(w); break;
        }
    }
    
    private static void WeaponStatsT1(int Id) {
        //weapon list
        //spells will be learned from books, but will be balanced with limited mana/cooldowns etc
        switch (Id) {
            //Melee weapons T1
            case 1:GetStats(dagger); break;
            case 2:GetStats(sword); break;
            case 3:GetStats(axe); break;
            case 4:GetStats(shank); break;
            case 5:GetStats(spear); break;
            case 6:GetStats(wristBlades); break;
            case 7:GetStats(cutlass); break;
            case 8:GetStats(club); break;
            
            //Ranged weapons T1
            case 9:GetStats(shortbow); break;
            case 10:GetStats(shurikan); break;
            case 11:GetStats(sling); break;
            case 12:GetStats(dartGun); break;
        }
   }
    
    private static void WeaponStatsT2(int Id) {
        //weapon list
        //spells will be learned from books, but will be balanced with limited mana/cooldowns etc
        switch (Id) {
            //Melee weapons T2
			case-1:return;
            case 1:GetStats(mace); break;
            case 2:GetStats(quarterstaff); break;
            case 3:GetStats(greatsword); break;
            case 4:GetStats(pike); break;
            case 5:GetStats(flail); break;
            
            //Ranged weapons T2
            case 6:GetStats(longbow); break;
            case 7:GetStats(crossbow); break;
            case 8:GetStats(javelin); break;
            case 9:GetStats(tomahawk); break;
           
            //Magic weapons T2
            case 10:GetStats(flame); break;
            case 11:GetStats(lightning); break;
            case 12:GetStats(frost); break;
        }
    }
    
    private static void WeaponStatsT3(int Id) {
        //weapon list
        //spells will be learned from books, but will be balanced with limited mana/cooldowns etc
        switch (Id) {
            //Melee weapons T3
            case 1: GetStats(warHammer); break;
            case 2:GetStats(battleAxe); break;
            case 3:GetStats(morningstar); break;
            case 4:GetStats(warScythe); break;
            
            //Ranged weapons T3
            case 5:GetStats(magicStaff); break;
            case 6:GetStats(compoundBow); break;
            case 7:GetStats(rCrossbow); break;
           
            //Magic weapons T3
            case 8:GetStats(sapping); break;
            case 9:GetStats(aura); break;
            case 10:GetStats(speed); break;
            case 11:GetStats(shift); break;
            case 12:GetStats(fireWall); break;
        }
    }
    
    private static void EnemyGen(int Id) {//enemy Id list
	enId = Id;
        switch (Id) {
                 //Battle(Enemy base stats, skeleton health)
            case 0:Battle(skeleton, 15); break;
            case 1:Battle(spider, 12); break;
            case 2:Battle(troll, 20); break;      
            case 3:Battle(snake, 6); break;             
            case 4:Battle(necromancer, 16); break;              
            case 5:Battle(wizard, 19); break;  
            case 6:Battle(skeletonArcher, 15); break;  
            case 7:Battle(goblin, 10); break;  
            case 8:Battle(outlaw, 15); break;  
            case 9:Battle(caveRat, 7); break;  
            case 10:Battle(wraith, 15); break;  
            case 11:Battle(zealot, 13); break;  
            case 12:Battle(demon, 16); break;  
            case 13:Battle(dragon, 29); break;  
            case 14:Battle(orc, 17); break;  
            case 15:Battle(vampire, 14); break;  
            case 16:Battle(leviathan, 28); break; 
            case 17:Battle(pixie, 9); break; 
            case 18:Battle(harpy, 11); break; 
            case 19:Battle(fallenHero, 16); break; 
            case 20:Battle(guardian, 18); break; 
            case 21:Battle(carnPlant, 12); break; 
            case 22:Battle(giant, 21); break; 
            case 23:Battle(looter, 14); break; 
            case 24:Battle(wyvern, 20); break; 
            case 25:Battle(cursedSoul, 17); break; 
            case 26:Battle(fElemental, 16); break; 
            case 27:Battle(wElemental, 16); break; 
            case 28:Battle(eElemental, 16); break; 
            case 29:Battle(aElemental, 16); break; 
            case 30:Battle(basilisk, 25); break; 
            case 31:Battle(golem, 18); break; 
            case 32:Battle(mimic, 13); break;
            case 33:Battle(manticore, 15); break;
            case 34:Battle(draugr, 16); break;
            case 35:Battle(legion, 12); break;
            case 36:Battle(griffin, 14); break;
            case 37:Battle(arvin, 1); break;
        }
    }
    
    private static int EnemyId(int[] enArr) {
        Random r = new Random();
        int Id = r.nextInt(enArr.length);
        return enArr[Id];
    }
    
    private static void Examine(int Id) {
        switch (Id) {
            case 0: System.out.println("  The skelton grins fearsomely at you, rage somehow evident in it's rigid bones.");
					System.out.println("  Tier: 1    Type: Melee    Highest stat: Damage"); break;
			case 1: System.out.println("  Deep in the dungeons, spiders are said to grow to gargantuan sizes. This one does not dissapoint.");
					System.out.println("  Tier: 1    Type: Melee    Highest stat: Speed"); break;
			case 2: System.out.println("  Cave trolls are mindless and savage, easily bent by the will of a powerful master to exact terrible destruction.");
					System.out.println("  Tier: 2    Type: Melee    Highest stat: Damage"); break;
			case 3: System.out.println("  This ancient and powerful snake has lived through the ages in darkness, devouring any who happen upon it.");
					System.out.println("  Tier: 1    Type: Melee    Highest stat: Speed"); break;
			case 4: System.out.print  ("  Necromancers are the most despised of all magicians, performing the darkest magics on the deceased to ");
					System.out.println("  bend into their undying slaves.");
					System.out.println("  Tier: 1    Type: Magic    Highest stat: Damage"); break;
			case 6: System.out.println("  The wizards who dwell here have been overpowered and corrupted by the evil magic of the dungeon");
					System.out.println("  Tier: 3    Type: Magic    Highest stat: Damage"); break;
			case 7: System.out.println("  This reanimated skeleton is not very different from others, other than it's use of a bow");
					System.out.println("  Tier: 1    Type: Melee    Highest stat: Damage"); break;
			case 8: System.out.println("  Perhaps the most infamous of foes, the goblin "); 
					System.out.println("  Tier: 1    Type: Melee    Highest stat: Damage"); break;
			//... on and on it goes.
            default:System.out.println("Congrats! this enemy doesn't exist"); break;
        }
    }
    
    /* Enemy Descriptions
 *Skeleton:          The skeleton looks energetic and fierceful despite it's boney build
 *Spider:            
 *Troll:             The troll is large and brutal, but seems to be weakened by it's slow-moving behaviour
 *Snake:             The snake is said to be a pure master of dodging, but is easily damaged due to it's thin build
 *Necromancer:       The necromancer is a quite a deadly opponent, but is hindered by it's laziness.
 *Wizard:            The wizard casts the most dangerous of attacks, which makes up for it's old age
 *Skeleton Archer:   With moderately striking damage and speed, the skeleton archer makes a worthy opponent
 *Goblin:            The goblin appears small and easily hurt, but it isn't the slowest or tamest foe
 *Outlaw:            Quick moving, heavy hitting and strongly built, the outlaw is a force to be reckoned with
 *Cave rat:          The cave rat is small and puny, but you've never seen something move so fast
 *Wraith:            The wraith immediatley spells trouble due to it's intimidating strength, but speed is this soul's downfall
 *Zealot:            The zealot has a devastating blow, but doesn't seem to have fast reflexes
 *Demon:             Fueled with pure anger, the demon is highly destructive and protected, but is surprisingly poor at dodging
 *Dragon:            Even though the dragon is arguebly the most powerful and heavily armoured creature, it's also one of the slowest
 *Orc:               Heavily fortified and armed, this lazy creature is more life-threatening than most think
 *Vampire:           The vampire's fangs are no doubt lethal, but it's speed is far from impressive
 *Leviathan:         The colossal leviathan can't swerve very well, but is no doubt one of the biggest threats in the dungeon
 *Pixie:             The pixie isn't the most harmful enemy, but can whizz past your attacks with ease
 *Harpy:             Not a very hard-hitting contender, but can certainly hold it's ground with it's agility
 *Fallen hero:       A master of his art, the fallen hero is malignant contestant, although the hero still hasn't aced ducking
 *Guardian:          As tall as the ceiling, and as tough as they come, the only thing stopping this behemoth is it's low momentum
 *Carnivorous plant: Not the weakest plant out there, but not the hardest to kill either
 *Giant:             The giant is larger and more brawny than you ever would've imagined, good thing they don't like to dart too much
 */

    private static void RoomGen(int Id) {   
        int enArr[];
        int enemyId;
        //only selects weapon up to + including current level
        Random t = new Random();
        Random q = new Random();
        int tier = 5;
        if (tier > 3) {
            tier = 3;
        }
        tier = t.nextInt(level)+1;
        switch(Id) {
            //decide tier of enemy here or up there ^
            case 1: System.out.println("  You enter a crypt, probably once connected to a catacomb");
                    switch(tier) {
						//each case reflects different tier enemy spawns
                        default: enArr = new int[]{0,1,3,7,9,17,18,23,25,32 }; break;
                        case 2:  enArr = new int[]{2,4,6,11,14,15,20,31}; break;
                        case 3:  enArr = new int[]{5,12,20,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 2: System.out.println("  You find yourself in a massive cavern");
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,4,7,9,17,18,23,25}; break;
                        case 2:  enArr = new int[]{2,6,11,14,15,19,22,31}; break;
                        case 3:  enArr = new int[]{10,12,13,16,24,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 3: System.out.println("  A long corridor stretches before you"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,9,17,23}; break;
                        case 2:  enArr = new int[]{2,6,8,11,14,15,31,34}; break;
                        case 3:  enArr = new int[]{5,10,20}; break;
                    }
            case 4: System.out.println("  An alter to some unknown deity stands wreathed in shadow");     
                    switch(tier) {
                        default: enArr = new int[]{0,1,21,25,35}; break;
                        case 2:  enArr = new int[]{11,28,29,31,33,34,36}; break;
                        case 3:  enArr = new int[]{10,12,13,16,20,24,26,27,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 5: System.out.println("  It seems you have stumbled upon a mass grave"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,21,23,25,35}; break;
                        case 2:  enArr = new int[]{4,6,15,19}; break;
                        case 3:  enArr = new int[]{5,10,12,26,27}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 6: System.out.println("  Shelves full of exotic potions and illegible tomes surround you"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,7,9,17,18,23}; break;
                        case 2:  enArr = new int[]{2,6,19,28,29}; break;
                        case 3:  enArr = new int[]{5,10,26,27}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 7: System.out.println("  An evil darkness lurks in the corners of the room"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,7,25,35}; break;
                        case 2:  enArr = new int[]{2,4,6,11,14,15,19,22,31}; break;
                        case 3:  enArr = new int[]{10,12,20}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 8: System.out.println("  Thick threads of spider silk coat the ceiling and walls around you"); 
                    enArr = new int[]{1}; //spider
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 9: System.out.println("  The floor is littered with eggs. It seems to be a nest of some sort"); 
                    switch(tier) {
                        default: enArr = new int[]{1,3}; break;
                        case 2:  enArr = new int[]{34,36}; break;
                        case 3:  enArr = new int[]{12,16,24,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 10:System.out.println("  You enter an unremarkable little cave, recently inhabited..."); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,7,9,23,37}; break;
                        case 2:  enArr = new int[]{2,6,8,11,14,15,22,31,33,34}; break;
                        case 3:  enArr = new int[]{5,13,20,24,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 11:System.out.println("  In the darkness you barely avoid falling into the underground lake in front of you");
                    switch(tier) {
                        default: enArr = new int[]{0,7,17,18,21,23}; break;
                        case 2:  enArr = new int[]{2,6,8,11,14,19,22,28,29,31}; break;
                        case 3:  enArr = new int[]{16,20,24,27,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 12:System.out.println("  Bones and other, fresher, remains, lay on the floor, surrounding a dark crevice in the wall"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,7,9,21,25}; break;
                        case 2:  enArr = new int[]{4,6,8,11,14,15,19,22,36}; break;
                        case 3:  enArr = new int[]{10,12,20,24}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 13:System.out.println("  Strange symbols cover all this room's surfaces"); 
                    switch(tier) {
                        default: enArr = new int[]{0,7,23,25,35}; break;
                        case 2:  enArr = new int[]{4,8,11,15,28,29}; break;
                        case 3:  enArr = new int[]{5,10,12,20,26,27}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 14:System.out.println("  It looks like there was once a forge here"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,7,23,31}; break;
                        case 2:  enArr = new int[]{2,6,8,11,19}; break;
                        case 3:  enArr = new int[]{5,10,26,27}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 15:System.out.println("  Rusted weapons and armour lay abandoned around you"); 
                    switch(tier) {
                        default: enArr = new int[]{0,7,23,25}; break;
                        case 2:  enArr = new int[]{2,6,8,14,19,22,31}; break;
                        case 3:  enArr = new int[]{5,10,13,20,24}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 16:System.out.println("  The ground before you falls away into a seemingly endless abyss"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,9,21,25}; break;
                        case 2:  enArr = new int[]{2,6,14,22,31,33,36}; break;
                        case 3:  enArr = new int[]{10,16,20,24,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 17:System.out.println("  The air around you suddenly cools"); 
                    switch(tier) {
                        default: enArr = new int[]{17,18,25,35}; break;
                        case 2:  enArr = new int[]{19,28,29,36}; break;
                        case 3:  enArr = new int[]{5,10,12,27}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 18:System.out.println("  In front of you is a once-great statue of some forgotten king"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,7,23}; break;
                        case 2:  enArr = new int[]{2,4,8,11,14,19,34}; break;
                        case 3:  enArr = new int[]{5,10,20}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 19:System.out.println("  A thick mist gathers around your feet"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,17,18,25,35}; break;
                        case 2:  enArr = new int[]{6,11,15,19,28,29,33,34}; break;
                        case 3:  enArr = new int[]{10,12,24,26,27}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 20:System.out.println("  You enter a mineshaft, long abandoned to rot and degradation"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,3,7,9,21,23}; break;
                        case 2:  enArr = new int[]{2,4,6,8,11,14,22,31,34}; break;
                        case 3:  enArr = new int[]{5,10,24,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 21:System.out.println("  You spot a chest placed discreetly in the corner"); //loot chest
                    GetLoot(null); 
                    isChest = true; break; 
            case 22:System.out.println("  You spot a chest placed discreetly in the corner"); //trap chest
                    enArr = new int[]{32}; //mimic
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break; 
            case 23:System.out.println("  A portal to some dark world opens in front of you"); 
                    switch(tier) {
                        default: enArr = new int[]{25,35}; break;
                        case 2:  enArr = new int[]{28,29}; break;
                        case 3:  enArr = new int[]{10,12,26,27}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 24:System.out.println("  The floor is littered with the old bodies of would-be heroes"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,21,23}; break;
                        case 2:  enArr = new int[]{2,6,8,11,14,15,19,22,31,33,34,36}; break;
                        case 3:  enArr = new int[]{10,13,20,24,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            case 25:System.out.println("  the doorway to this room has strange runes scrawled across it - probably a warning"); 
                    switch(tier) {
                        default: enArr = new int[]{0,1,7,17,18,25}; break;
                        case 2:  enArr = new int[]{2,4,6,11,14,28,29,34}; break;
                        case 3:  enArr = new int[]{10,12,13,20,24,26,27,30}; break;
                    }
                    enemyId = EnemyId(enArr);
                    EnemyGen(enemyId); break;
            default:System.out.println("  A shopkeeper sits looking somewhat bored at his stall");
                    Delay(null);
                    Shop(null); break;
        }
    }
    
    private void LWeapons(int Id) {
        //legendary weapons
        switch (Id) {
            case 1:; //Excalibur
            break;
            case 2:; //Carved Basilisk Knife
            break;
            case 3:; //Meat-Cleaver of the Damned
            break;
            case 4:; //spell: Dragonfire
            break;
            case 5:; //Gatling Crossbow
            break;
            case 6:; //spell: Freeze time
            break;
            case 7:; //The Lobotimizer
            break;
            case 8:; //Chain-and-Sickle 
            break;
            case 9:; //Mithril Hamaxe
            break;
            case 10:; //Fists of Fury
            break;
            case 11:; //Shadow Daggers
        }
    }
}