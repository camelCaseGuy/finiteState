
import java.util.Scanner;
import java.util.Arrays;

abstract class State {
  static State initialize, accumulate, subtract, add, equalize, error, current;

  void enter() {
  };

  void update(Scanner scanner) {
  };
}

class Initialize extends State {
  void enter() {
    System.out.println("Welcome to the calculator. Now in Initialize state.");
    System.out.println("Enter a character, 1-9: ");
  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
      current = accumulate;
    } else {
      current = error;
    }
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
      current = accumulate;
    } else if (input.equals("-")) {
      current = subtract;
    } else if (input.equals("+")) {
      current = add;
    } else if (input.equals("=")) {
      current = equalize;
    } else {
      current = error;
    }

  }
}

class Subtract extends State {
  void enter() {
    System.out.println("Now in Subtract state.");
    System.out.println("Enter a character, 0-9 : ");
  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
      current = accumulate;
    } else {
      current = error;
    }
  }
}

class Add extends State {
  void enter() {
    System.out.println("Now in Add state.");
    System.out.println("Enter a character, 0-9 : ");
  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").contains(input)) {
      current = accumulate;
    } else {
      current = error;
    }
  }
}

class Equalize extends State {
  void enter() {
    System.out.println("Now in Equalize state.");
    System.out.println("Your evaluation: ");
    System.out.println("Press Y to do another calculation, or any other key to exit : ");

  }

  void update(Scanner scanner) {
    String input = scanner.next();
    if (input.equals("Y")) {
      current = initialize;
    } else {
      System.exit(0);
    }
  }
}

class Error extends State {
  void enter() {
    System.out.println("Now in Error state.");
    System.out.println("Sorry, you have an error. Please follow the instructions.");
  }

  void update(Scanner scanner) {
    current = initialize;
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
    while (true) {
      State.current.enter();
      State.current.update(scanner);
    }

  }
}
