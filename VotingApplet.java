import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VotingApplet extends Applet implements ActionListener {
    private Map<String, Integer> candidates;
    private Set<String> votedUsers;
    private TextField nameField;
    private Label resultLabel;
    private Label voteLabel;

    public void init() {
        candidates = new LinkedHashMap<>();
        votedUsers = new HashSet<>();

        candidates.put("Alice", 0);
        candidates.put("Bob", 0);
        candidates.put("Charlie", 0);

        setLayout(new GridLayout(6, 1)); 

        add(new Label("Enter your name:"));
        nameField = new TextField(20);
        add(nameField);

        for (String candidate : candidates.keySet()) {
            Button voteButton = new Button("Vote for " + candidate);
            voteButton.setActionCommand(candidate); 
            voteButton.addActionListener(this);     
            add(voteButton);
        }

        voteLabel = new Label("");
        add(voteLabel);

        resultLabel = new Label("");
        add(resultLabel);
    }

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim().toLowerCase();
        String chosenCandidate = e.getActionCommand();

        if (name.isEmpty()) {
            voteLabel.setText("Please enter your name.");
            return;
        }

        if (votedUsers.contains(name)) {
            voteLabel.setText("You have already voted!");
            return;
        }

        candidates.put(chosenCandidate, candidates.get(chosenCandidate) + 1);
        votedUsers.add(name);

        voteLabel.setText("Thanks for voting for " + chosenCandidate + "!");

        StringBuilder results = new StringBuilder("Current votes: ");
        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            results.append(entry.getKey()).append(": ").append(entry.getValue()).append("  ");
        }
        resultLabel.setText(results.toString());
    }
}
