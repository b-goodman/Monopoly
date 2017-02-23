/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.MutableComboBoxModel;
//import javax.swing.ListModel;
//import monopoly.Card;
import monopoly.ChanceCards;
import monopoly.Card;
import monopoly.Cell;
import monopoly.Cells;
import monopoly.ChestCards;
import monopoly.Enums.ActionPrimary;
import monopoly.Enums.ActionType;
import monopoly.Player;
import monopoly.Players;
import monopoly.Rules;
import monopoly.Token;

/**
 *
 * @author bgood_000
 */
public class Setup extends javax.swing.JFrame {

    private DefaultListModel<String> cellListModel;
    private DefaultListModel<String> chanceDeckListModel;
    private DefaultListModel<String> chestDeckListModel;
    private MutableComboBoxModel<String> tokenBoxModel;
    private DefaultListModel<String> playerListModel;

    /**
     * Creates new form Setup
     *
     * @throws java.io.IOException
     */
    public Setup() throws IOException {

        //ChestCards.init();
        initComponents();

        setupDefaultCells();

        setupPlayers();

        setupDefaultChanceDeck();
        setupDefaultChestDeck();

        setupDefaultRules();

    }

    private void setupPlayers() {
        Players.add("Player 1", 0, Token.TOPHAT);
        setupTokenBox();
        setupPlayerList();
    }

    private void resetPlayers() {
        Players.getPlayers().clear();
        setupPlayers();
    }

    public void refreshListPlayerFields(int selectionIndex) {
        Player selectedPlayer = Players.get((Integer) selectionIndex);
        playerNameEditField.setText(selectedPlayer.getName());
        playerStartingCashEditField.setText(String.valueOf(selectedPlayer.getCash()));
    }

    public void refreshListPlayerFields(Player selectedPlayer) {
        playerNameEditField.setText(selectedPlayer.getName());
        playerStartingCashEditField.setText(String.valueOf(selectedPlayer.getCash()));
    }

    public void updateListPlayerFields(Player selectedPlayer) {
        selectedPlayer.setName(playerNameEditField.getText());
        selectedPlayer.setCash(Integer.valueOf(playerStartingCashEditField.getText()));
    }

    private void setupTokenBox() {
        //instantiate box model and set to token box
        tokenBoxModel = new DefaultComboBoxModel();
        tokenBox.setModel(tokenBoxModel);
        //populate box model will all tokens
        for (Token avaliableToken : Players.getAvaliableTokens()) {
            tokenBoxModel.addElement(avaliableToken.toString());
        }
    }

    private void refreshTokenBox() {
        tokenBox.removeAllItems();
        for (Token avaliableToken : Players.getAvaliableTokens()) {
            tokenBoxModel.addElement(avaliableToken.toString());
        }
    }

    /**
     * Done once to initalize player list picker
     */
    private void setupPlayerList() {
        //instantiate and set default list model
        playerListModel = new DefaultListModel();
        playerList.setModel(playerListModel);
        //populate model with default player(s)
        for (Player player : Players.getPlayers().values()) {
            playerListModel.addElement(player.getName());
        }
        //set initial selection
        playerList.setSelectedIndex(0);
        //update edit fields
        refreshListPlayerFields(0);
    }

    private void setupDefaultCells() throws IOException {
        cellListModel = new DefaultListModel();
        Cells.LOCATIONS.clear();
        Cells.init();
        cellList.setModel(cellListModel);
        for (Cell cell : Cells.LOCATIONS.values()) {
            cellListModel.addElement((String) cell.getName());
        }
        cellListModel.remove(0);
        cellList.setSelectedIndex(0);

    }

    private void setupDefaultChanceDeck() throws IOException {
        chanceDeckListModel = new DefaultListModel();
        ChanceCards.CHANCE_CARD_LIB.clear();
        chanceDeckListModel.clear();
        ChanceCards.init();
        chanceDeckList.setModel(chanceDeckListModel);
        for (Card card : ChanceCards.CHANCE_CARD_LIB) {
            chanceDeckListModel.addElement((String) card.getCardContent().get(1));
        }
        //set default selection
        chanceDeckList.setSelectedIndex(0);
        //(re)initalize description panel
        chanceDescriptionText.setText(generateDesc(ChanceCards.CHANCE_CARD_LIB.get(0)));
    }

    private void setupDefaultChestDeck() throws IOException {
        chestDeckListModel = new DefaultListModel();
        ChestCards.CHEST_CARD_LIB.clear();
        chestDeckListModel.clear();
        ChestCards.init();
        chestDeckList.setModel(chestDeckListModel);
        for (Card card : ChestCards.CHEST_CARD_LIB) {
            chestDeckListModel.addElement((String) card.getCardContent().get(1));
        }
        //set default selection
        chestDeckList.setSelectedIndex(0);
        //(re)initalize description panel
        chestDescriptionText.setText(generateDesc(ChestCards.CHEST_CARD_LIB.get(0)));
    }

    private void setupDefaultRules() throws IOException {
        //load default rules
        Rules.init();
        //assign variables
        passGoBonus.setText(String.valueOf(Rules.getPassGoCredit()));
        enableGoLandingBonus.setSelected(Rules.isGoLandBonusEnabled());
        enableFreeParkingBonus.setSelected(Rules.isFreeParkingBonusEnabled());
        enableBonusCap.setSelected(Rules.isFreeParkingBonusLimitEnabled());
        enableFiniteResources.setSelected(Rules.isImprovementResourcesFinite());
        houseAmount.setText(String.valueOf(Rules.getImprovementAmountHouse()));
        hotelAmount.setText(String.valueOf(Rules.getImprovementAmountHotel()));
        enableEvenBuild.setSelected(Rules.isPropertyEvenBuildEnabled());
        hotelPrerequisiteField.setText(String.valueOf(Rules.getPropertyHotelReq()));
        improvementDepreciationField.setText(String.valueOf(Rules.getPropertyResalePenaltyValue()));
        setCompletionBonusField.setText(String.valueOf(Rules.getGroupCompletRentBonus()));
        enableMortgageInterest.setSelected(Rules.isMortgageInterestEnabled());
        mortgageInterestRateField.setText(String.valueOf(Rules.getMortgageInterestRate()));
        enableSpeeding.setSelected(Rules.isSpeedingEnabled());
        speedLimitField.setText(String.valueOf(Rules.getDoublesSpeedingLimit()));
        maxJailTermField.setText(String.valueOf(Rules.getMaxJailTerm()));
        bailFeeField.setText(String.valueOf(Rules.getJailLeaveFee()));

    }

    private String generateDesc(Card selectedCard) {

        ActionType cardType = ActionType.valueOf((String) selectedCard.getCardContent().get(2));
        //ActionPrimary cardParamater = ActionPrimary.valueOf((String) selectedCard.getCardContent().get(3));
        String returnDesc = null;

        switch (cardType) {
            case TRANSITION_ABS:
                returnDesc = "Advances the player to the stated position.  Player collects salary if passing GO";
                break;
            case TRANSITION_REL:
                switch (ActionPrimary.valueOf((String) selectedCard.getCardContent().get(3))) {
                    case NEXT:
                        returnDesc = "Advances the player to the next stated property type.  Player collects salary if passing GO";
                        break;
                    case GO:
                        returnDesc = "Moves the player by an amount of spaces given.  Negative values will move the player backwards. Player collects salary if passing GO";
                        break;
                }
                break;
            case DEBIT_ABS:
                returnDesc = "Player pays the bank an amount of cash equal to the stated value";
                break;
            case DEBIT_REL:
                switch (ActionPrimary.valueOf((String) selectedCard.getCardContent().get(3))) {
                    case PAY_EACH:
                        returnDesc = "Player pays each other active players an amount of cash equal to the stated value";
                        break;
                    case REPAIR:
                        returnDesc = "Player pays an ammount of cash to the bank determined by the quantity of improvements present on all properties owned by the player at time of draw";
                        break;
                }
                break;
            case CREDIT_ABS:
                returnDesc = "The player recieves an amount of cash from the bank equal to the stated value";
                break;
            case CREDIT_REL:
                returnDesc = "The player recieves an amount of cash from each other player equal to the stated value";
                break;
            case JAIL:
                switch (ActionPrimary.valueOf((String) selectedCard.getCardContent().get(3))) {
                    case IN:
                        returnDesc = "The player is sent directly to jail without passing GO or collecting salary";
                        break;
                    case OUT:
                        returnDesc = "The player may present this card whilst in jail to leave on the next turn without paying any fines";
                        break;
                }
                break;
            default:
                returnDesc = "Select a card to view description";
                break;
        }
        return returnDesc;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        setupTabPane = new javax.swing.JTabbedPane();
        gameBoardTabPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        cellList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        saveCellEditButton = new javax.swing.JButton();
        resetCellEditButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cellTypeEditBox = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cellValueEditField = new javax.swing.JFormattedTextField();
        cellNameEditField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        cellMortgageEditField = new javax.swing.JFormattedTextField();
        jLabel20 = new javax.swing.JLabel();
        groupCharacterEditBox = new javax.swing.JComboBox<>();
        groupColorEditBox = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        cardDecksTabPanel = new javax.swing.JPanel();
        cardDeckSelection = new javax.swing.JTabbedPane();
        chanceDeckViewPane = new javax.swing.JPanel();
        chanceRemoveButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        chanceDeckList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        chanceDescriptionText = new javax.swing.JTextArea();
        chestDeckViewPane = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        chestDeckList = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        chestDescriptionText = new javax.swing.JTextArea();
        chestCardRemove = new javax.swing.JButton();
        playersTabPanel = new javax.swing.JPanel();
        playerStartingConditionsPanel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        playerStartingCashEditField = new javax.swing.JFormattedTextField();
        playerConditionsSaveButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Name = new javax.swing.JLabel();
        playerNameEditField = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerList = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        tokenBox = new javax.swing.JComboBox<>();
        playerNameField = new javax.swing.JTextField();
        addPlayerButton = new javax.swing.JButton();
        deletePlayerButton = new javax.swing.JButton();
        rulesTabPanel = new javax.swing.JPanel();
        jailRulesPanel = new javax.swing.JPanel();
        bailFeeField = new javax.swing.JFormattedTextField();
        speedLimitField = new javax.swing.JFormattedTextField();
        enableSpeeding = new javax.swing.JCheckBox();
        maxJailTermField = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        propertyRulesPanel = new javax.swing.JPanel();
        mortgageInterestRateField = new javax.swing.JFormattedTextField();
        hotelPrerequisiteField = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        enableFiniteResources = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        hotelAmount = new javax.swing.JFormattedTextField();
        enableEvenBuild = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        improvementDepreciationField = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        setCompletionBonusField = new javax.swing.JFormattedTextField();
        enableMortgageInterest = new javax.swing.JCheckBox();
        houseAmount = new javax.swing.JFormattedTextField();
        cashRulesPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        enableGoLandingBonus = new javax.swing.JCheckBox();
        enableBonusCap = new javax.swing.JCheckBox();
        enableFreeParkingBonus = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        passGoBonus = new javax.swing.JFormattedTextField();
        goLandingBonusValue = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        bonusCapAmountField = new javax.swing.JFormattedTextField();
        resetButton = new javax.swing.JButton();
        configItems = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jLabel2.setText("jLabel2");

        jCheckBox2.setText("jCheckBox2");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel15.setText("jLabel15");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane7.setViewportView(cellList);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Name");

        saveCellEditButton.setText("Save Changes");

        resetCellEditButton.setText("Reset Changes");

        jLabel17.setText("Type");

        cellTypeEditBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Value");

        cellValueEditField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel19.setText("Mortgage");

        cellMortgageEditField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel20.setText("Group");

        groupCharacterEditBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        groupColorEditBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cellNameEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cellValueEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cellMortgageEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(saveCellEditButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetCellEditButton))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cellTypeEditBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupCharacterEditBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupColorEditBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cellNameEditField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cellValueEditField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(cellMortgageEditField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cellTypeEditBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(groupCharacterEditBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupColorEditBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(resetCellEditButton)
                    .addComponent(saveCellEditButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout gameBoardTabPanelLayout = new javax.swing.GroupLayout(gameBoardTabPanel);
        gameBoardTabPanel.setLayout(gameBoardTabPanelLayout);
        gameBoardTabPanelLayout.setHorizontalGroup(
            gameBoardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameBoardTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        gameBoardTabPanelLayout.setVerticalGroup(
            gameBoardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameBoardTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(gameBoardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );

        setupTabPane.addTab("Game Board", gameBoardTabPanel);

        chanceRemoveButton.setText("Remove Card");
        chanceRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chanceRemoveButtonActionPerformed(evt);
            }
        });

        chanceDeckList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        chanceDeckList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chanceDeckListMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(chanceDeckList);

        chanceDescriptionText.setEditable(false);
        chanceDescriptionText.setColumns(20);
        chanceDescriptionText.setLineWrap(true);
        chanceDescriptionText.setRows(5);
        chanceDescriptionText.setWrapStyleWord(true);
        jScrollPane4.setViewportView(chanceDescriptionText);

        javax.swing.GroupLayout chanceDeckViewPaneLayout = new javax.swing.GroupLayout(chanceDeckViewPane);
        chanceDeckViewPane.setLayout(chanceDeckViewPaneLayout);
        chanceDeckViewPaneLayout.setHorizontalGroup(
            chanceDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chanceDeckViewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chanceDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chanceDeckViewPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                    .addGroup(chanceDeckViewPaneLayout.createSequentialGroup()
                        .addComponent(chanceRemoveButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        chanceDeckViewPaneLayout.setVerticalGroup(
            chanceDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chanceDeckViewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chanceDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chanceDeckViewPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(chanceRemoveButton))
                    .addGroup(chanceDeckViewPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cardDeckSelection.addTab("Chance", chanceDeckViewPane);

        chestDeckList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        chestDeckList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chestDeckListMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(chestDeckList);

        chestDescriptionText.setColumns(20);
        chestDescriptionText.setLineWrap(true);
        chestDescriptionText.setRows(5);
        chestDescriptionText.setWrapStyleWord(true);
        jScrollPane6.setViewportView(chestDescriptionText);

        chestCardRemove.setText("Remove Card");
        chestCardRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chestCardRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chestDeckViewPaneLayout = new javax.swing.GroupLayout(chestDeckViewPane);
        chestDeckViewPane.setLayout(chestDeckViewPaneLayout);
        chestDeckViewPaneLayout.setHorizontalGroup(
            chestDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chestDeckViewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chestDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chestDeckViewPaneLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                    .addGroup(chestDeckViewPaneLayout.createSequentialGroup()
                        .addComponent(chestCardRemove)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        chestDeckViewPaneLayout.setVerticalGroup(
            chestDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chestDeckViewPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chestDeckViewPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chestCardRemove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardDeckSelection.addTab("Communnity Chest", chestDeckViewPane);

        javax.swing.GroupLayout cardDecksTabPanelLayout = new javax.swing.GroupLayout(cardDecksTabPanel);
        cardDecksTabPanel.setLayout(cardDecksTabPanelLayout);
        cardDecksTabPanelLayout.setHorizontalGroup(
            cardDecksTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDecksTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardDeckSelection)
                .addContainerGap())
        );
        cardDecksTabPanelLayout.setVerticalGroup(
            cardDecksTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDecksTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardDeckSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 323, Short.MAX_VALUE)
                .addContainerGap())
        );

        cardDeckSelection.getAccessibleContext().setAccessibleName("Chance");

        setupTabPane.addTab("Card Decks", cardDecksTabPanel);

        playerStartingConditionsPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Starting Cash");

        playerStartingCashEditField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        playerConditionsSaveButton.setText("Save Changes");
        playerConditionsSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerConditionsSaveButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Reset Changes");

        Name.setText("Name");

        javax.swing.GroupLayout playerStartingConditionsPanelLayout = new javax.swing.GroupLayout(playerStartingConditionsPanel);
        playerStartingConditionsPanel.setLayout(playerStartingConditionsPanelLayout);
        playerStartingConditionsPanelLayout.setHorizontalGroup(
            playerStartingConditionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerStartingConditionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerStartingConditionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(playerStartingConditionsPanelLayout.createSequentialGroup()
                        .addComponent(playerConditionsSaveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(playerStartingConditionsPanelLayout.createSequentialGroup()
                        .addComponent(Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerNameEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(playerStartingConditionsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playerStartingCashEditField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        playerStartingConditionsPanelLayout.setVerticalGroup(
            playerStartingConditionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerStartingConditionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playerStartingConditionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name)
                    .addComponent(playerNameEditField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(playerStartingConditionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(playerStartingCashEditField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(playerStartingConditionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerConditionsSaveButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        playerList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                playerListMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(playerList);

        tokenBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        playerNameField.setText("Player Name");

        addPlayerButton.setText("+");
        addPlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPlayerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playerNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tokenBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tokenBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPlayerButton))
                .addContainerGap())
        );

        deletePlayerButton.setText("Delete Player");
        deletePlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePlayerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deletePlayerButton))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(deletePlayerButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout playersTabPanelLayout = new javax.swing.GroupLayout(playersTabPanel);
        playersTabPanel.setLayout(playersTabPanelLayout);
        playersTabPanelLayout.setHorizontalGroup(
            playersTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playersTabPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(playerStartingConditionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        playersTabPanelLayout.setVerticalGroup(
            playersTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playersTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(playersTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playerStartingConditionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setupTabPane.addTab("Players", playersTabPanel);

        bailFeeField.setText("50");

        speedLimitField.setText("3");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableSpeeding, org.jdesktop.beansbinding.ELProperty.create("${selected}"), speedLimitField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        enableSpeeding.setSelected(true);
        enableSpeeding.setText("Jail by Speeding");

        maxJailTermField.setText("3");

        jLabel5.setText("Speed Limit");

        jLabel11.setText("Maximum Jail Term");

        jLabel12.setText("Bail Fee");

        javax.swing.GroupLayout jailRulesPanelLayout = new javax.swing.GroupLayout(jailRulesPanel);
        jailRulesPanel.setLayout(jailRulesPanelLayout);
        jailRulesPanelLayout.setHorizontalGroup(
            jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jailRulesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enableSpeeding)
                    .addGroup(jailRulesPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(speedLimitField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addGroup(jailRulesPanelLayout.createSequentialGroup()
                        .addGroup(jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bailFeeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(maxJailTermField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jailRulesPanelLayout.setVerticalGroup(
            jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jailRulesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enableSpeeding)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(speedLimitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxJailTermField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jailRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bailFeeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        mortgageInterestRateField.setText("10");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableMortgageInterest, org.jdesktop.beansbinding.ELProperty.create("${selected}"), mortgageInterestRateField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        hotelPrerequisiteField.setText("4");
        hotelPrerequisiteField.setMaximumSize(new java.awt.Dimension(40, 20));
        hotelPrerequisiteField.setMinimumSize(new java.awt.Dimension(40, 20));
        hotelPrerequisiteField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hotelPrerequisiteFieldActionPerformed(evt);
            }
        });

        jLabel9.setText("Improvement Depreciation");

        jLabel8.setText("Hotel Requirement");

        enableFiniteResources.setSelected(true);
        enableFiniteResources.setText("Finite Resources");

        jLabel7.setText("Hotels");

        hotelAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        hotelAmount.setText("12");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableFiniteResources, org.jdesktop.beansbinding.ELProperty.create("${selected}"), hotelAmount, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        enableEvenBuild.setSelected(true);
        enableEvenBuild.setText("Even-Build Improvments");

        jLabel10.setText("Completion Rent Bonus");

        improvementDepreciationField.setText("2");

        jLabel6.setText("Houses");

        jLabel13.setText("Interest Rate");

        setCompletionBonusField.setText("2");
        setCompletionBonusField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setCompletionBonusFieldActionPerformed(evt);
            }
        });

        enableMortgageInterest.setSelected(true);
        enableMortgageInterest.setText("Mortgage Interest");

        houseAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        houseAmount.setText("32");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableFiniteResources, org.jdesktop.beansbinding.ELProperty.create("${selected}"), houseAmount, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout propertyRulesPanelLayout = new javax.swing.GroupLayout(propertyRulesPanel);
        propertyRulesPanel.setLayout(propertyRulesPanelLayout);
        propertyRulesPanelLayout.setHorizontalGroup(
            propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                        .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(enableMortgageInterest)
                            .addComponent(enableFiniteResources)
                            .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                                        .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(hotelAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                            .addComponent(houseAmount))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)))
                                    .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                                        .addComponent(mortgageInterestRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13))))
                            .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                                .addComponent(hotelPrerequisiteField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addComponent(enableEvenBuild))
                        .addGap(44, 44, 44))
                    .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                        .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(improvementDepreciationField, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(setCompletionBonusField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        propertyRulesPanelLayout.setVerticalGroup(
            propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertyRulesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enableFiniteResources)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(houseAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hotelAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(enableEvenBuild)
                .addGap(18, 18, 18)
                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hotelPrerequisiteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(improvementDepreciationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setCompletionBonusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(enableMortgageInterest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertyRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mortgageInterestRateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel1.setText("Pass GO Bonus");

        enableGoLandingBonus.setText("Land on GO Bonus");

        enableBonusCap.setText("Bonus Cap");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableFreeParkingBonus, org.jdesktop.beansbinding.ELProperty.create("${selected}"), enableBonusCap, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        enableFreeParkingBonus.setSelected(false);
        enableFreeParkingBonus.setText("Free Parking Bonus");
        enableFreeParkingBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableFreeParkingBonusActionPerformed(evt);
            }
        });

        jLabel4.setText("Amount");

        passGoBonus.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        passGoBonus.setText("200");
        passGoBonus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passGoBonusActionPerformed(evt);
            }
        });

        goLandingBonusValue.setText("200");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableGoLandingBonus, org.jdesktop.beansbinding.ELProperty.create("${selected}"), goLandingBonusValue, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        jLabel3.setText("Amount");

        bonusCapAmountField.setText("500");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, enableBonusCap, org.jdesktop.beansbinding.ELProperty.create("${selected}"), bonusCapAmountField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout cashRulesPanelLayout = new javax.swing.GroupLayout(cashRulesPanel);
        cashRulesPanel.setLayout(cashRulesPanelLayout);
        cashRulesPanelLayout.setHorizontalGroup(
            cashRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashRulesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cashRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cashRulesPanelLayout.createSequentialGroup()
                        .addComponent(passGoBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(enableGoLandingBonus)
                    .addComponent(enableFreeParkingBonus)
                    .addGroup(cashRulesPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(cashRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cashRulesPanelLayout.createSequentialGroup()
                                .addComponent(goLandingBonusValue, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(enableBonusCap)
                            .addGroup(cashRulesPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(bonusCapAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)))))
                .addGap(14, 14, 14))
        );
        cashRulesPanelLayout.setVerticalGroup(
            cashRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashRulesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cashRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passGoBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(enableGoLandingBonus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cashRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goLandingBonusValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(enableFreeParkingBonus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enableBonusCap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cashRulesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bonusCapAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout rulesTabPanelLayout = new javax.swing.GroupLayout(rulesTabPanel);
        rulesTabPanel.setLayout(rulesTabPanelLayout);
        rulesTabPanelLayout.setHorizontalGroup(
            rulesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rulesTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cashRulesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(propertyRulesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jailRulesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        rulesTabPanelLayout.setVerticalGroup(
            rulesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rulesTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rulesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(propertyRulesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(rulesTabPanelLayout.createSequentialGroup()
                        .addComponent(jailRulesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cashRulesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setupTabPane.addTab("Rules", rulesTabPanel);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        configItems.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Next >");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(configItems, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(setupTabPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setupTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetButton)
                    .addComponent(configItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passGoBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passGoBonusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passGoBonusActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        try {
            int selectedTab = setupTabPane.getSelectedIndex();

            switch (selectedTab) {
                case 1:
                    int subSelectedTabDecks = cardDeckSelection.getSelectedIndex();
                    switch (subSelectedTabDecks) {
                        case 0:
                            setupDefaultChanceDeck();
                            break;
                        case 1:
                            setupDefaultChestDeck();
                            break;
                    }
                    break;

                case 2:
                    playerListModel.clear();
                    Players.getPlayers().clear();
                    setupPlayers();

                //default rules
                case 3:
                    setupDefaultRules();
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_resetButtonActionPerformed

    private void enableFreeParkingBonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableFreeParkingBonusActionPerformed
        if (enableBonusCap.isSelected() == true) {
            enableBonusCap.setSelected(false);
        }
        if (enableFreeParkingBonus.isSelected() == true) {
            enableBonusCap.setEnabled(true);
        } else if (enableFreeParkingBonus.isSelected() == false) {
            enableBonusCap.setEnabled(false);
        }
    }//GEN-LAST:event_enableFreeParkingBonusActionPerformed

    private void hotelPrerequisiteFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hotelPrerequisiteFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hotelPrerequisiteFieldActionPerformed

    private void setCompletionBonusFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setCompletionBonusFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setCompletionBonusFieldActionPerformed

    private void chanceRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chanceRemoveButtonActionPerformed
        int index = chanceDeckList.getSelectedIndex();
        chanceDeckListModel.remove(index);
        ChanceCards.CHANCE_CARD_LIB.remove(index);

        int size = chanceDeckListModel.getSize();

        if (size == 0) { //Nobody's left, disable firing.
            setEnabled(false);

        } else { //Select an index.
            if (index == chanceDeckListModel.getSize()) {
                //removed item in last position
                index--;
            }

            chanceDeckList.setSelectedIndex(index);
            chanceDeckList.ensureIndexIsVisible(index);
            chanceDescriptionText.setText(generateDesc(ChanceCards.CHANCE_CARD_LIB.get(index)));

        }
    }//GEN-LAST:event_chanceRemoveButtonActionPerformed

    private void chanceDeckListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chanceDeckListMousePressed
        Card selectedCard = ChanceCards.CHANCE_CARD_LIB.get(chanceDeckList.getSelectedIndex());
        chanceDescriptionText.setText(generateDesc(selectedCard));
    }//GEN-LAST:event_chanceDeckListMousePressed

    private void chestCardRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chestCardRemoveActionPerformed
        int index = chestDeckList.getSelectedIndex();
        chestDeckListModel.remove(index);
        ChestCards.CHEST_CARD_LIB.remove(index);

        int size = chestDeckListModel.getSize();

        if (size == 0) { //Nobody's left, disable firing.
            setEnabled(false);

        } else { //Select an index.
            if (index == chestDeckListModel.getSize()) {
                //removed item in last position
                index--;
            }

            chestDeckList.setSelectedIndex(index);
            chestDeckList.ensureIndexIsVisible(index);
            chestDescriptionText.setText(generateDesc(ChestCards.CHEST_CARD_LIB.get(index)));

        }
    }//GEN-LAST:event_chestCardRemoveActionPerformed

    private void chestDeckListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chestDeckListMousePressed
        Card selectedCard = ChestCards.CHEST_CARD_LIB.get(chestDeckList.getSelectedIndex());
        chestDescriptionText.setText(generateDesc(selectedCard));
    }//GEN-LAST:event_chestDeckListMousePressed

    private void deletePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePlayerButtonActionPerformed

        if (playerListModel.getSize() == 1) {
            System.out.println("There must be at least 1 player");
        } else {
            int index = playerList.getSelectedIndex();
            playerListModel.remove(index);
            Players.PLAYERS.remove(index);

            refreshTokenBox();
            //now go through all the players and reset their ID's
            int playerAmount = Players.getPlayers().size();

            //Integer playerKey = 0;
            Integer newKey = 0;

            for (Integer playerKey = 0; playerKey <= playerAmount; playerKey++) {
                Object tempRemove = Players.getPlayers().remove(playerKey);
                if (tempRemove != null) {
                    Players.getPlayers().put(newKey, (Player) tempRemove);
                    newKey++;
                }
            }

            //System.out.println(Players.getPlayers().keySet());
            int size = playerListModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                setEnabled(false);

            } else { //Select an index.
                if (index == playerListModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                playerList.setSelectedIndex(index);
                playerList.ensureIndexIsVisible(index);

                //TODO
                //update text content of player inspector
                refreshListPlayerFields(index);

            }
        }

    }//GEN-LAST:event_deletePlayerButtonActionPerformed

    private void addPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPlayerButtonActionPerformed
        //get selected token, name and designate new player ID
        Token playerToken = Token.valueOf((String) tokenBox.getSelectedItem());
        String playerName = playerNameField.getText();
        //new ID is largest existing ID + 1
        Integer playerID = Collections.max(Players.getPlayers().keySet()) + 1;
        //call Player constructor with above variables and add name to list
        Players.add(playerName, playerID, playerToken);
        playerListModel.addElement(playerName);
        //update token box
        refreshTokenBox();
        //select newly added player
        playerList.setSelectedIndex((playerListModel.getSize()));

    }//GEN-LAST:event_addPlayerButtonActionPerformed

    private void playerListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerListMousePressed
        Player selectedPlayer = Players.get(playerList.getSelectedIndex());
//        System.out.println(selectedPlayer.getPlayerID());
//        System.out.println(playerList.getSelectedIndex());
        //int selectedPlayerCash = selectedPlayer.getCash();
        refreshListPlayerFields(selectedPlayer);
    }//GEN-LAST:event_playerListMousePressed

    private void playerConditionsSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerConditionsSaveButtonActionPerformed
        int index = playerList.getSelectedIndex();
        Player targetPlayer = Players.get(index);
        updateListPlayerFields(targetPlayer);
        //update name in model
        playerListModel.removeElementAt(index);
        playerListModel.add(index, playerNameEditField.getText());
        playerList.setSelectedIndex(index);
    }//GEN-LAST:event_playerConditionsSaveButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ChanceCards.shuffleDeck();
        ChestCards.shuffleDeck();
//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Log().setVisible(true);
        });
    }//GEN-LAST:event_jButton1ActionPerformed

//    /**
    //     * @param args the command line arguments
    //     */
    //    public static void main(String args[]) {
    //        /* Set the Nimbus look and feel */
    //        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    //        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
    //         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
    //         */
    //        try {
    //            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    //                if ("Nimbus".equals(info.getName())) {
    //                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
    //                    break;
    //                }
    //            }
    //        } catch (ClassNotFoundException ex) {
    //            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (InstantiationException ex) {
    //            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (IllegalAccessException ex) {
    //            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //            java.util.logging.Logger.getLogger(Setup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //        }
    //        //</editor-fold>
    //        //</editor-fold>
    //        //</editor-fold>
    //        //</editor-fold>
    //
    //        /* Create and display the form */
    //        java.awt.EventQueue.invokeLater(new Runnable() {
    //            public void run() {
    //                new Setup().setVisible(true);
    //            }
    //        });
    //    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Name;
    private javax.swing.JButton addPlayerButton;
    private javax.swing.JFormattedTextField bailFeeField;
    private javax.swing.JFormattedTextField bonusCapAmountField;
    private javax.swing.JTabbedPane cardDeckSelection;
    private javax.swing.JPanel cardDecksTabPanel;
    private javax.swing.JPanel cashRulesPanel;
    private javax.swing.JList<String> cellList;
    private javax.swing.JFormattedTextField cellMortgageEditField;
    private javax.swing.JTextField cellNameEditField;
    private javax.swing.JComboBox<String> cellTypeEditBox;
    private javax.swing.JFormattedTextField cellValueEditField;
    private javax.swing.JList<String> chanceDeckList;
    private javax.swing.JPanel chanceDeckViewPane;
    private javax.swing.JTextArea chanceDescriptionText;
    private javax.swing.JButton chanceRemoveButton;
    private javax.swing.JButton chestCardRemove;
    private javax.swing.JList<String> chestDeckList;
    private javax.swing.JPanel chestDeckViewPane;
    private javax.swing.JTextArea chestDescriptionText;
    private javax.swing.JComboBox<String> configItems;
    private javax.swing.JButton deletePlayerButton;
    private javax.swing.JCheckBox enableBonusCap;
    private javax.swing.JCheckBox enableEvenBuild;
    private javax.swing.JCheckBox enableFiniteResources;
    private javax.swing.JCheckBox enableFreeParkingBonus;
    private javax.swing.JCheckBox enableGoLandingBonus;
    private javax.swing.JCheckBox enableMortgageInterest;
    private javax.swing.JCheckBox enableSpeeding;
    private javax.swing.JPanel gameBoardTabPanel;
    private javax.swing.JFormattedTextField goLandingBonusValue;
    private javax.swing.JComboBox<String> groupCharacterEditBox;
    private javax.swing.JComboBox<String> groupColorEditBox;
    private javax.swing.JFormattedTextField hotelAmount;
    private javax.swing.JFormattedTextField hotelPrerequisiteField;
    private javax.swing.JFormattedTextField houseAmount;
    private javax.swing.JFormattedTextField improvementDepreciationField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel jailRulesPanel;
    private javax.swing.JFormattedTextField maxJailTermField;
    private javax.swing.JFormattedTextField mortgageInterestRateField;
    private javax.swing.JFormattedTextField passGoBonus;
    private javax.swing.JButton playerConditionsSaveButton;
    private javax.swing.JList<String> playerList;
    private javax.swing.JFormattedTextField playerNameEditField;
    private javax.swing.JTextField playerNameField;
    private javax.swing.JFormattedTextField playerStartingCashEditField;
    private javax.swing.JPanel playerStartingConditionsPanel;
    private javax.swing.JPanel playersTabPanel;
    private javax.swing.JPanel propertyRulesPanel;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton resetCellEditButton;
    private javax.swing.JPanel rulesTabPanel;
    private javax.swing.JButton saveCellEditButton;
    private javax.swing.JFormattedTextField setCompletionBonusField;
    private javax.swing.JTabbedPane setupTabPane;
    private javax.swing.JFormattedTextField speedLimitField;
    private javax.swing.JComboBox<String> tokenBox;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
