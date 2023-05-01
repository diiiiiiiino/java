package 디자인패턴.컴포지트패턴;

public class MenuTestDrive {
	public static void main(String args[]) {
		
		MenuComponent aMenu =
			new Menu("A MENU", "a");

		MenuComponent aMenu2 =
				new Menu("A-1 MENU", "a-1");

		MenuComponent bMenu =
			new Menu("B MENU", "b");
  
		MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
  
		allMenus.add(aMenu);
		allMenus.add(bMenu);

		aMenu.add(aMenu2);

		aMenu.add(new MenuItem(
			"a menuItem",
			"a menuItem",
			true,
			2.99));

		bMenu.add(new MenuItem(
				"b menuItem",
				"b menuItem",
				true,
				2.99));

 
		Waitress waitress = new Waitress(allMenus);
   
		waitress.printVegetarianMenu();
		//waitress.printMenu();
 
	}
}
