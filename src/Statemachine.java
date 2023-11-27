
import java.util.Scanner;
import java.util.Arrays;

abstract class State {
  static State initialize, accumulate, subtract, add, equalize, error, current;

  static int total = 0; // Specify the data type as int
  static int currentInput = 0; // Specify the data type as int
  static String symbol = "";

  void enter() {
  };

  void update(Scanner scanner) {
  };
}

class Initialize extends State {
  void enter() {
    System.out.println("Welcome to the calculator. Now in Initialize state.");
    System.out.println("Enter a character, 1-9: ");
    currentInput = 0;
    total = 0;
  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
      current = accumulate;
      currentInput = Integer.parseInt(input);
    } else {
      current = error;
    }
    State.current.enter();
    State.current.update(scanner);
  }
}

class Accumulate extends State {
  void enter() {
    System.out.println("Now in Accumulate state.");
    System.out.println("Enter a character, 0-9 or -,+,= : ");
  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
      currentInput = currentInput * 10 + Integer.parseInt(input);
    } else if (input.equals("-")) {
      current = subtract;
    } else if (input.equals("+")) {
      current = add;
    } else if (input.equals("=")) {
      current = equalize;
    } else {
      current = error;
    }

    
    // if (symbol.equals("-")) {
    //   total = total - currentInput;
    // } else if (symbol.equals("+")) {
    //   total = total + currentInput;
    // } else if (symbol.equals("")) {
    //   total = currentInput;
    // }

    // currentInput = 0;
    
    State.current.enter();
    State.current.update(scanner);
  }
}

class Subtract extends State {
  void enter() {
    System.out.println("Now in Subtract state.");
    if (symbol.equals("-")) {
      total = total - currentInput;
    } else if (symbol.equals("+")) {
      total = total + currentInput;
    } else {
      total = currentInput;
    }
    symbol = "-";
    System.out.println("Enter a character, 0-9 : ");
  }

  void update(Scanner scanner) {
    String input = scanner.next();

    if (Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
      currentInput = Integer.parseInt(input);
      current = accumulate;
    } else {
      current = error;
    }
    State.current.enter();
    State.current.update(scanner);
  }
}

class Add extends State {
  void enter() {
    System.out.println("Now in Add state.");

    if (symbol.equals("-")) {
      total = total - currentInput;
    } else if (symbol.equals("+")) {
      total = total + currentInput;
    } else {
      total = currentInput;
    }

    symbol = "+";
    
    System.out.println("Total so far: " + total);
    System.out.println("Enter a character, 0-9 : ");
  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
      currentInput = Integer.parseInt(input);
      current = accumulate;
    } else {
      current = error;
    }
    State.current.enter();
    State.current.update(scanner);
  }
}

class Equalize extends State {
  void enter() {
    System.out.println("Now in Equalize state.");
    if (symbol.equals("-")) {
      total = total - currentInput;
    } else if (symbol.equals("+")) {
      total = total + currentInput;
    } else if (symbol.equals("")) {
      total = currentInput;
    }
    symbol = "";
    System.out.println("Your total: " + total);
    System.out.println("Press Y to do another calculation, or any other key to exit : ");

  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (input.toLowerCase().equals("y")) {
      current = initialize;
      State.current.enter();
      State.current.update(scanner);
    } else {
      System.exit(0);
    }
  }
}

class Error extends State {
  void enter() {
    System.out.println("Now in Error state.");
    System.out.println("Sorry, you have an error. Please follow the instructions.");
    System.out.println("Press Y to do another calculation, or any other key to exit : ");
  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (input.equals("Y")) {
      current = initialize;
      State.current.enter();
      State.current.update(scanner);
    } else {
      System.exit(0);
    }
  }
}

public class Statemachine {
  public static void main(String[] args) {
    State.initialize = new Initialize();
    State.accumulate = new Accumulate();
    State.subtract = new Subtract();
    State.add = new Add();
    State.equalize = new Equalize();
    State.error = new Error();
    State.current = State.initialize;
    Scanner scanner = new Scanner(System.in);
    State.current.enter();
    State.current.update(scanner);

  }
}
