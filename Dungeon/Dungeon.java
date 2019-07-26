import java.util.*;
//import javax.swing.*;

public class Dungeon {
	//initialize Variables
	static int level = 1; //highest weapon tier that can be generated. Adds onto enemy damage
	static double healthMult = 1.0; //enemy health = base x this
	static int potionHeal = 60; //potion health regen
	static int potionMana = 60; //mana health regen
	static int hPotionCost = 10;
	static int mPotionCost = 10;
	static int weaponCost = 10;
	static boolean isChest = false;
	static boolean changeW = true;
	static boolean wGen = false;
	static boolean shop = false;
	//initialise Classes
	//Player and enemy class
	static Player pl = new Player();
	
	//Melee weapons - speed determines dodge chance
    static Weapon dagger         = new Melee("Dagger", 7, 6);            
    static Weapon sword          = new Melee("Sword", 9, 5);             
	static Weapon mace           = new Melee("Mace", 12, 4);		        
    static Weapon axe            = new Melee("Axe", 13, 4);		        
    static Weapon warHammer      = new Melee("War Hammer", 14, 3);        
	static Weapon quarterstaff   = new Melee("Quarterstaff", 12, 7);      
	static Weapon greatsword     = new Melee("Greatsword", 13, 4);	    
    static Weapon shank          = new Melee("Shank", 8, 8);		        
    static Weapon spear          = new Melee("Spear", 10, 6);		        
    static Weapon wristBlades    = new Melee("Wrist Blades", 9, 9);       
	static Weapon battleAxe       = new Melee("Battle Axe", 16, 3);          
    static Weapon morningstar    = new Melee("Morningstar", 15, 4);       
    static Weapon pike           = new Melee("Pike", 13, 5);              
    static Weapon cutlass        = new Melee("Cutlass", 11, 6);           
    static Weapon chain          = new Melee("Chain", 14, 6);		        
    static Weapon ballChain      = new Melee("Ball and Chain", 16, 1);      
	static Weapon warScythe      = new Melee("War Scythe", 14 ,2);        
			
	//Ranged weapons - ^ same
    static Weapon shortbow       = new Ranged("Shortbow", 11, 7);         
    static Weapon longbow        = new Ranged("Longbow", 13, 5);          
    static Weapon shurikan       = new Ranged("Shurikan", 9, 8);         
	static Weapon crossbow       = new Ranged("Crossbow", 14, 3);
    static Weapon magicStaff     = new Melee("Magic Staff", 15, 6); 	
           
	//Magic weapons - speed increases chance to hit on top of weapon speed 
    static Weapon flame          = new Magic("Fireball", 12, 7);	        
    static Weapon lightning      = new Magic("Lightning Bolt", 10, 10);    
    static Weapon frost          = new Magic("Ice Beam", 14, 4);          
    static Weapon sapping        = new Magic("Drain Speed", 10, 7); //decrease en speed  
    static Weapon aura           = new Magic("Defensive Aura", 10, 10); //decrease en damage   
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
    static Weapon fanatic          = new Melee("Fanatic", 9, 4);             
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
    static Weapon wyrm             = new Melee("Wyrm", 10, 3);               
    static Weapon cursedSoul       = new Melee("Cursed Soul", 8, 4);        
    static Weapon fElemental       = new Ranged("Fire Elemental", 9, 5);    
    static Weapon wElemental       = new Ranged("Water Elemental", 9, 5);   
    static Weapon eElemental       = new Ranged("Earth Elemental", 9, 5);   
    static Weapon aElemental       = new Ranged("Air Elemental", 9, 5);     
    static Weapon basilisk         = new Melee("Basilisk", 11, 3);           
    static Weapon golem            = new Melee("Rock Golem", 10, 2);         
    static Weapon mimic            = new Melee("Mimic", 9, 4);
	
    public static void main(String[] args) {
        //set up beginning of game
		Scanner s = new Scanner(System.in);
		int weapon = pl.getWeapon(); //gives Player class weapon info from the get go
		WeaponStats(weapon);
		changeW = false;
        System.out.println("");
        System.out.println("  Do you want to enter The Dungeon?");
        System.out.println("  =================================");
        System.out.println("  [Start] [Quit] [Help] [Controls]");
        String input = "";
        while (!input.equalsIgnoreCase("start")) {
            //option select
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
                System.out.println("");
                System.out.println("  Do you want to enter The Dungeon?");
                System.out.println("  =================================");
                System.out.println("  [Start] [Quit] [Help] [Controls]");
            }
            else {
                Help(null);
                System.out.println("");
                System.out.println("  Do you want to enter The Dungeon?");
                System.out.println("  =================================");
                System.out.println("  [Start] [Quit] [Help] [Controls]");
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
            }
        }
		
		NextLevel(null);
        
        //outputs to JFrame instead of console
        /*JFrame f = new JFrame("ASCII");
        JLabel l = new  JLabel("room");
        l.setBounds(300,100,100,100);
        f.add(l);
        f.setSize(101,101);
        f.setLayout(null);
        f.setVisible(true);*/
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
        System.out.println("  To select [Option], type");
		System.out.println("  option OR o. Case does not");
		System.out.println("  matter. When the marker is");
		System.out.println("_ <- there, it is a pause and");
		System.out.println("  is continued by pressing enter");
		System.out.println("  several options can also be");
		System.out.println("  accessed here, like quit and help (?)");
        Delay(null);
    }
    
    private static void Controls(String[] args) {
        System.out.println("");
        System.out.println("  To select option: type whatever's in [here]");
        System.out.println("  i = inventory");
        System.out.println("  m = map");
        System.out.println("  s = stats");
        System.out.println("");
        System.out.println("  note: nothing works at this stage of the program");
        System.out.println("  They may or may not be implemented later on");
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
        Scanner s = new Scanner(System.in);
        String delay = s.nextLine();
        if (delay.equalsIgnoreCase("?")) {
            Help(null);
        }
		if (delay.equalsIgnoreCase("quit")) {
			System.exit(1);
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
    
    private static int EnemyId(int[] enArr) {
        Random r = new Random();
        int Id = r.nextInt(enArr.length);
        return enArr[Id];
    }
    
	private static void Battle(Weapon e, int h) {
		Enemy en = new Enemy();
		e.newWeapon();
		en.setHealth((int) Math.round(h*healthMult)); //set enemy health
		Delay(null);
		System.out.println("  A " + e.getName() + " appears!");
		Delay(null);
		
		while (en.getHealth() > 0) {
			Scanner s = new Scanner(System.in);
			System.out.println("  What will you do?");
			System.out.println("  =================");
			System.out.println("  [Weapon] | [Spell]");
			System.out.println("  - - - - - - - - -");
			System.out.println("  [Health] | [Mana]");
			
			System.out.print("  ");
			String input = s.nextLine();
			//String input = "w"; speed-run melee attacks
			if (input.equalsIgnoreCase("weapon") || input.equalsIgnoreCase("w")) {
				enDodgeChance(e, en);
				if (en.isDead == true) {
					pl.addScore(100+h); //+100 for winning, + damage dealt
					System.out.println("");
					GetLoot(null); //moneys
					System.out.println("  You enter the next room");
					Delay(null);
					return;
				}
				Delay(null);
				plDodgeChance(e);
			}
			else if (input.equalsIgnoreCase("spell") || input.equalsIgnoreCase("s")) {
				//attack with spell stats
				Delay(null);
				pl.hit(e);
			}
			else if (input.equalsIgnoreCase("health") || input.equalsIgnoreCase("h")) {
				if (pl.getHPotions() > 0) {
					System.out.println("  You feel your body being repaired");
					pl.setHealth(potionHeal);
					pl.setHPotions(-1);
					Delay(null);
					plDodgeChance(e);
				} else {
					System.out.println("");
					System.out.println("  You don't have any more health potions!");
				}
				
			}
			else if (input.equalsIgnoreCase("mana") || input.equalsIgnoreCase("m")) {
				if (pl.getMPotions() > 0) {
					System.out.println("  Your magik is restored");
					pl.setMana(potionMana);
					Delay(null);
					plDodgeChance(e);
				} else {
					System.out.println("");
					System.out.println("  You don't have any more mana potions!");
				}
			}
			else {
				System.out.println("  Not a valid option. Enter '?' for help");
			}
			
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
			en.hit(e);
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
			pl.hit(e);
		} else {
			System.out.println("  You dodge the "+e.getName()+"'s attack!");
		}
	}
	
	private static void Shop(String[] args) {
		Scanner s = new Scanner(System.in);
		String input = "";
		String subInput = "";
		Random r = new Random();
		int weapon = r.nextInt(23)+1; //not including spells atm
		wGen = true;
		
		while (!input.equalsIgnoreCase("leave") && !input.equalsIgnoreCase("l")) {
			System.out.println("  What Do you want to buy?");
			System.out.println("  ========================");
			System.out.println("  [Potions][Weapons][Leave]");
			System.out.print("  ");
			input = s.nextLine();
			System.out.println("");
			
			if (input.equalsIgnoreCase("potions") || input.equalsIgnoreCase("p")) {
				System.out.println("  What Do you want to buy?");
				System.out.println("  ========================");
				System.out.println("  [Health:"+hPotionCost+"]  [Mana:"+mPotionCost+"]");
				System.out.println("  Your balance: "+pl.getCash());
				System.out.print("  ");
				subInput = s.nextLine();
				System.out.println("");
			}
				
			if (subInput.equalsIgnoreCase("health") || subInput.equalsIgnoreCase("h")) {
				
				if (pl.getCash() > hPotionCost) {
					pl.setHPotions(1);
					pl.setCash(-hPotionCost);
					hPotionCost = (int) Math.round(hPotionCost * 1.5);
					System.out.println("  You purchased a health potion");
					System.out.println("  You have "+pl.getHPotions()+" health potions");
				} else {
					System.out.println("  You don't have enough money!");
				}
			}	
			
			if (subInput.equalsIgnoreCase("mana") || subInput.equalsIgnoreCase("m")) {
				if (pl.getCash() > mPotionCost) {
					pl.setMPotions(1);
					pl.setCash(-mPotionCost);
					mPotionCost = (int) Math.round(mPotionCost * 1.5);
					System.out.println("  You purchased a health potion");
					System.out.println("  You have "+pl.getMPotions()+" health potions");
				} else {
					System.out.println("  You don't have enough money!");
				}
			}
			
			if (input.equalsIgnoreCase("weapons") || input.equalsIgnoreCase("w")) {
				shop = true;
				WeaponStats(weapon);
				wGen = false;
				shop = false;
			}
			
			if (input.equalsIgnoreCase("leave") || input.equalsIgnoreCase("l")) {
				System.out.println("  The shopkeeper wishes you luck");
				
			} else {
				//System.out.println("  Not a valid option. Enter '?' for help");
				//commented out because of dodgy else if logic
			}
			Delay(null);
		}
	}
	
	private static void GetLoot(String[] args) {
		Random r = new Random();
		int wChance = r.nextInt(10)+1;
		int weapon;
		if (wChance == 1) {
			System.out.println("  You find a weapon!");
			Delay(null);
			weapon = r.nextInt(23)+1; //not including spells atm
			WeaponStats(weapon);
		} else {
			int loot = (r.nextInt(11)+5) * level;
			if (isChest == true) {
				Delay(null);
				System.out.println("  you open the chest to see what's in it");
				pl.setCash(loot * 2);
				isChest = false;
				Delay(null);
			}
			System.out.println("  You find "+loot+" coins");
			Delay(null);
			pl.setCash(loot);
		}
	}
	
	private static void SetStats(Weapon w) {
		pl.setDamage(w.getDamage()); 
		pl.setSpeed(w.speed);
		pl.setName(w.name);
		w.SendQualityName();
	}
	
	private static void GetStats(Weapon w) {
		Scanner s = new Scanner(System.in);
		String input = "";
		if (changeW == true) {
			w.newWeapon();
			SetStats(w);
		} else if (shop == true) {
			if (wGen == true) {
				w.newWeapon();
			}
			System.out.println("  Do you want to buy this weapon?");
			System.out.println("  ===============================");
			System.out.println("  Cost: "+weaponCost);
			System.out.println("");
			System.out.println("  Current Weapon:");
			System.out.println("  Name: "+pl.getName());
			System.out.println("  Quality: "+pl.getQName());
			System.out.println("  Damage: "+pl.getDamage()); 
			System.out.println("  Speed: "+pl.getSpeed());
			System.out.println("");
			System.out.println("  New Weapon:");
			System.out.println("  Name: "+w.name);
			System.out.println("  Quality: "+w.qualityN);
			System.out.println("  Damage: "+w.getDamage()); 
			System.out.println("  Speed: "+w.getSpeed());
			System.out.println("");
			System.out.print("  ");
			input = s.nextLine();
			System.out.println("");
			
			if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
					System.out.println("  You purchase the weapon");
					SetStats(w);
					
				}
				if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
					System.out.println("  You keep your current weapon");
				} else {
					System.out.println("  Not a valid option. Enter '?' for help");
				}
				System.out.println("");
		} else {
			while (!input.equalsIgnoreCase("yes") && !input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("n")) {
				w.newWeapon();
				System.out.println("");
				System.out.println("  Current Weapon:");
				System.out.println("  Name: "+pl.getName());
				System.out.println("  Quality: "+pl.getQName());
				System.out.println("  Damage: "+pl.getDamage()); 
				System.out.println("  Speed: "+pl.getSpeed());
				System.out.println("");
				System.out.println("  New Weapon:");
				System.out.println("  Name: "+w.name);
				System.out.println("  Quality: "+w.qualityN);
				System.out.println("  Damage: "+w.getDamage()); 
				System.out.println("  Speed: "+w.getSpeed());
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
				if (input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
					System.out.println("  You keep your current weapon");
				} else {
					System.out.println("  Not a valid option. Enter '?' for help");
				}
				System.out.println("");
			}
		}
	}
	
	private static void WeaponStats(int Id) {
        //weapon list
        //spells will be learned from books, but will be balanced with limited mana/cooldowns etc
		switch (Id) {
			//Melee weapons
            case 1: GetStats(dagger); break;
            case 2: GetStats(sword); break;
			case 3: GetStats(mace); break;
            case 4: GetStats(axe); break;
            case 5: GetStats(warHammer); break;
			case 6: GetStats(quarterstaff); break;
			case 7: GetStats(greatsword); break;
            case 8: GetStats(shank); break;
            case 9: GetStats(spear); break;
            case 10:GetStats(wristBlades); break;
            case 11:GetStats(battleAxe); break;
			case 12:GetStats(morningstar); break;
            case 13:GetStats(pike); break;
            case 14:GetStats(cutlass); break;
            case 15:GetStats(chain); break;
            case 16:GetStats(ballChain); break;
			case 17:GetStats(warScythe); break;
            
			//Ranged weapons
            case 19:GetStats(shortbow); break;
            case 20:GetStats(longbow); break;
            case 21:GetStats(shurikan); break;
			case 22:GetStats(crossbow); break;
			case 23:GetStats(magicStaff); break;
           
		    //Magic weapons
            case 24:GetStats(flame); break;
            case 25:GetStats(lightning); break;
            case 26:GetStats(frost); break;
            case 27:GetStats(sapping); break;
            case 28:GetStats(aura); break;
            case 29:GetStats(speed); break;
            case 30:GetStats(shift); break;
            case 31:GetStats(fireWall); break;
        }
    }
	
    private static void EnemyGen(int Id) {//enemy Id list
        switch (Id) {
			     //Battle(Enemy base stats, skeleton health)
            case 0:Battle(skeleton, 10); break;
            case 1:Battle(spider, 8); break;
            case 2:Battle(troll, 20); break;      
            case 3:Battle(snake, 7); break; 			
            case 4:Battle(necromancer, 14); break;  			
            case 5:Battle(wizard, 13); break;  
            case 6:Battle(skeletonArcher, 12); break;  
            case 7:Battle(goblin, 10); break;  
            case 8:Battle(outlaw, 15); break;  
            case 9:Battle(caveRat, 7); break;  
            case 10:Battle(wraith, 15); break;  
            case 11:Battle(fanatic, 13); break;  
            case 12:Battle(demon, 16); break;  
            case 13:Battle(dragon, 23); break;  
            case 14:Battle(orc, 17); break;  
            case 15:Battle(vampire, 14); break;  
            case 16:Battle(leviathan, 22); break; 
            case 17:Battle(pixie, 9); break; 
            case 18:Battle(harpy, 11); break; 
            case 19:Battle(fallenHero, 16); break; 
            case 20:Battle(guardian, 18); break; 
            case 21:Battle(carnPlant, 12); break; 
            case 22:Battle(giant, 19); break; 
            case 23:Battle(looter, 14); break; 
            case 24:Battle(wyrm, 20); break; 
            case 25:Battle(cursedSoul, 18); break; 
            case 26:Battle(fElemental, 16); break; 
            case 27:Battle(wElemental, 16); break; 
            case 28:Battle(eElemental, 16); break; 
            case 29:Battle(aElemental, 16); break; 
            case 30:Battle(basilisk, 21); break; 
            case 31:Battle(golem, 20); break; 
            case 32:Battle(mimic, 13); break;
        }
    }
	
	private static void RoomGen(int Id) {   
        int enArr[];
        int enemyId;
        
        switch(Id) {
            case -1:System.out.println("  This should not appear. if it does, roomId wasn't called"); 
					enArr = new int[]{-1}; //numbers in curly brackets reperesents possible enemy spawns in room via Id#
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 1: System.out.println("  You enter a crypt, probably once connected to a catacomb");
					enArr = new int[]{0,4,6,19,23,25};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 2: System.out.println("  You find yourself in a massive cavern"); 
					enArr = new int[]{1,2,7,13,17,18,22,29};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 3: System.out.println("  A long corridor stretches before you"); 
					enArr = new int[]{1,3,9,15,17,20,31};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 4: System.out.println("  An alter to some unknown deity stands wreathed in shadow");     
					enArr = new int[]{4,5,10,11,12,25};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 5: System.out.println("  It seems you have stumbled upon a mass grave"); 
					enArr = new int[]{0,6,9,15,19,23};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 6: System.out.println("  shelves full of exotic potions and illegible tomes surround you"); 
					enArr = new int[]{4,5,11,12,30};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 7: System.out.println("  An evil darkness lurks in the corners of the room"); 
					enArr = new int[]{10,11,12,25};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 8: System.out.println("  thick threads of spider silk coat the ceiling and walls around you"); 
					enArr = new int[]{1};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 9: System.out.println("  The floor is littered with eggs. It seems to be a nest of some sort"); 
					enArr = new int[]{1,3,13,30};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 10:System.out.println("  You enter an unremarable little cave, recently inhabited..."); 
					enArr = new int[]{2,5,7,8,11,14,15,23};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 11:System.out.println("  In the darkness you barely avoid falling into the underground lake in front of you");
					enArr = new int[]{16,17,18,24,27,30};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 12:System.out.println("  Bones and other, fresher, remains, lay on the floor, surrounding a dark crevice in the wall"); 
					enArr = new int[]{2,3,13,16,21,22,24,30};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 13:System.out.println("  Strange symbols cover all this room's surfaces"); 
					enArr = new int[]{4,5,11,12,15,20,25};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 14:System.out.println("  It looks like there was once a forge here"); 
					enArr = new int[]{0,6,7,8,14,15,23,26};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 15:System.out.println("  rusted weapons and armour lay abandoned around you"); 
					enArr = new int[]{0,6,7,8,14,15,23};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 16:System.out.println("  The ground before you falls away into a seemingly endless abyss"); 
					enArr = new int[]{10,12,13,17,18,26,27,28,29};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 17:System.out.println("  The air around you suddenly cools"); 
					enArr = new int[]{4,10,12,29};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 18:System.out.println("  in front of you is a once-great statue of some forgotten hero"); 
					enArr = new int[]{0,6,9,11,19,20,28};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 19:System.out.println("  A thick mist gathers around your feet"); 
					enArr = new int[]{4,5,10,12,17,29,31};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 20:System.out.println("  You enter a mineshaft, long abandoned to rot and degradation"); 
					enArr = new int[]{0,1,2,3,7,9,23,31};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 21:System.out.println("  You spot a chest placed discreetly in the corner"); //loot chest
					GetLoot(null); 
					isChest = true; break; 
            case 22:System.out.println("  You spot a chest placed discreetly in the corner"); //trap chest
					enArr = new int[]{32};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break; 
            case 23:System.out.println("  A portal to some dark world floats omniously in front of you"); 
					enArr = new int[]{10,11,12};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 24:System.out.println("  The floor is littered with the old bodies of would-be heroes"); 
					enArr = new int[]{0,6,10,19,23};
					enemyId = EnemyId(enArr);
					EnemyGen(enemyId); break;
            case 25:System.out.println("  the doorway to this room has strange runes scrawled across it - probably a warning"); 
					enArr = new int[]{2,13,20,21,24,30,31};
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
            case 2:; //Carved Basilisk Tooth
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
