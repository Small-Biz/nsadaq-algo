package com.smallbiz.nasdaqalgo.main;

import com.ib.client.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class IBAlgoBot implements EWrapper {
    private final EClientSocket client;
    private final EReaderSignal signal;
    private EReader reader;
    private final int clientId = 0;
    private final int tickerId = 1;
    private final int orderId = 1001;

    // Moving average settings
    private final int SHORT_WINDOW = 10;  // Fast moving average period
    private final int LONG_WINDOW = 50;   // Slow moving average period
    private final LinkedList<Double> priceHistory = new LinkedList<>();

    private boolean isConnected = false;
    private boolean isPositionOpen = false;

    public IBAlgoBot() {
        signal = new EJavaSignal();
        client = new EClientSocket(this, signal);
    }

    public void connect() {
        while (!isConnected) {
            try {
                System.out.println("🔄 Attempting to connect to IBKR...");
                client.eConnect("127.0.0.1", 7497, clientId);

                reader = new EReader(client, signal);
                reader.start();

                new Thread(() -> {
                    while (client.isConnected()) {
                        signal.waitForSignal();
                        try {
                            reader.processMsgs();
                        } catch (Exception e) {
                            System.err.println("⚠ Error processing messages: " + e.getMessage());
                        }
                    }
                }).start();

                isConnected = true;
                System.out.println("✅ Connected to IBKR!");
            } catch (Exception e) {
                System.err.println("❌ Connection failed, retrying in 5 seconds...");
                sleep(5000);
            }
        }
    }

    public void startMarketData() {
        Contract contract = new Contract();
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.currency("USD");
        contract.exchange("SMART");

        client.reqMktData(tickerId, contract, "", false, false, null);
    }

    @Override
    public void tickPrice(int tickerId, int field, double price, TickAttrib attribs) {
        if (field == TickType.LAST.index() && price > 0) {
            System.out.println("💰 Latest Price: " + price);
            processPrice(price);
        }
    }

    @Override
    public void tickSize(int i, int i1, Decimal decimal) {

    }

    @Override
    public void tickOptionComputation(int i, int i1, int i2, double v, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {

    }

    private void processPrice(double price) {
        priceHistory.add(price);
        if (priceHistory.size() > LONG_WINDOW) {
            priceHistory.removeFirst(); // Keep only the last N prices
        }

        if (priceHistory.size() >= LONG_WINDOW) {
            double shortMA = calculateMovingAverage(SHORT_WINDOW);
            double longMA = calculateMovingAverage(LONG_WINDOW);

            System.out.printf("📊 Short MA: %.2f | Long MA: %.2f\n", shortMA, longMA);

            if (!isPositionOpen && shortMA > longMA) {
                placeOrder("BUY", price);
                isPositionOpen = true;
            } else if (isPositionOpen && shortMA < longMA) {
                placeOrder("SELL", price);
                isPositionOpen = false;
            }
        }
    }

    private double calculateMovingAverage(int window) {
        return priceHistory.stream().skip(priceHistory.size() - window).mapToDouble(a -> a).average().orElse(0);
    }

    private void placeOrder(String action, double price) {
        System.out.println("📢 Placing " + action + " order at $" + price);

        Order order = new Order();
        order.orderId(orderId);
        order.action(action);
        order.orderType("MKT");
        order.totalQuantity(Decimal.get(10));
        order.transmit(true);

        Contract contract = new Contract();
        contract.symbol("AAPL");
        contract.secType("STK");
        contract.currency("USD");
        contract.exchange("SMART");

        client.placeOrder(orderId, contract, order);
    }

    @Override
    public void error(Exception e) {
        System.err.println("⚠ Error: " + e.getMessage());
    }

    @Override
    public void error(String s) {

    }

    @Override
    public void error(int i, int i1, String s, String s1) {

    }

//    @Override
//    public void error(int id, int errorCode, String errorMsg) {
//        System.err.println("⚠ IBKR Error [" + errorCode + "]: " + errorMsg);
//    }

    @Override
    public void connectionClosed() {
        System.err.println("🔴 Connection lost! Attempting to reconnect...");
        isConnected = false;
        connect();
    }

    @Override
    public void connectAck() {

    }

    @Override
    public void positionMulti(int i, String s, String s1, Contract contract, Decimal decimal, double v) {

    }

    @Override
    public void positionMultiEnd(int i) {

    }

    @Override
    public void accountUpdateMulti(int i, String s, String s1, String s2, String s3, String s4) {

    }

    @Override
    public void accountUpdateMultiEnd(int i) {

    }

    @Override
    public void securityDefinitionOptionalParameter(int i, String s, int i1, String s1, String s2, Set<String> set, Set<Double> set1) {

    }

    @Override
    public void securityDefinitionOptionalParameterEnd(int i) {

    }

    @Override
    public void softDollarTiers(int i, SoftDollarTier[] softDollarTiers) {

    }

    @Override
    public void familyCodes(FamilyCode[] familyCodes) {

    }

    @Override
    public void symbolSamples(int i, ContractDescription[] contractDescriptions) {

    }

    @Override
    public void historicalDataEnd(int i, String s, String s1) {

    }

    @Override
    public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {

    }

    @Override
    public void tickNews(int i, long l, String s, String s1, String s2, String s3) {

    }

    @Override
    public void smartComponents(int i, Map<Integer, Map.Entry<String, Character>> map) {

    }

    @Override
    public void tickReqParams(int i, double v, String s, int i1) {

    }

    @Override
    public void newsProviders(NewsProvider[] newsProviders) {

    }

    @Override
    public void newsArticle(int i, int i1, String s) {

    }

    @Override
    public void historicalNews(int i, String s, String s1, String s2, String s3) {

    }

    @Override
    public void historicalNewsEnd(int i, boolean b) {

    }

    @Override
    public void headTimestamp(int i, String s) {

    }

    @Override
    public void histogramData(int i, List<HistogramEntry> list) {

    }

    @Override
    public void historicalDataUpdate(int i, Bar bar) {

    }

    @Override
    public void rerouteMktDataReq(int i, int i1, String s) {

    }

    @Override
    public void rerouteMktDepthReq(int i, int i1, String s) {

    }

    @Override
    public void marketRule(int i, PriceIncrement[] priceIncrements) {

    }

    @Override
    public void pnl(int i, double v, double v1, double v2) {

    }

    @Override
    public void pnlSingle(int i, Decimal decimal, double v, double v1, double v2, double v3) {

    }

    @Override
    public void historicalTicks(int i, List<HistoricalTick> list, boolean b) {

    }

    @Override
    public void historicalTicksBidAsk(int i, List<HistoricalTickBidAsk> list, boolean b) {

    }

    @Override
    public void historicalTicksLast(int i, List<HistoricalTickLast> list, boolean b) {

    }

    @Override
    public void tickByTickAllLast(int i, int i1, long l, double v, Decimal decimal, TickAttribLast tickAttribLast, String s, String s1) {

    }

    @Override
    public void tickByTickBidAsk(int i, long l, double v, double v1, Decimal decimal, Decimal decimal1, TickAttribBidAsk tickAttribBidAsk) {

    }

    @Override
    public void tickByTickMidPoint(int i, long l, double v) {

    }

    @Override
    public void orderBound(long l, int i, int i1) {

    }

    @Override
    public void completedOrder(Contract contract, Order order, OrderState orderState) {

    }

    @Override
    public void completedOrdersEnd() {

    }

    @Override
    public void replaceFAEnd(int i, String s) {

    }

    @Override
    public void wshMetaData(int i, String s) {

    }

    @Override
    public void wshEventData(int i, String s) {

    }

    @Override
    public void historicalSchedule(int i, String s, String s1, String s2, List<HistoricalSession> list) {

    }

    @Override
    public void userInfo(int i, String s) {

    }

    private void sleep(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException ignored) {}
    }

    public static void main(String[] args) {
        IBAlgoBot bot = new IBAlgoBot();
        bot.connect();
        bot.startMarketData();

        // Keep the bot running
        while (true) {
            bot.sleep(10000);
        }
    }

    // Empty overrides (required by EWrapper)
    @Override public void tickGeneric(int tickerId, int tickType, double value) {}
    @Override public void tickString(int tickerId, int tickType, String value) {}

    @Override
    public void tickEFP(int i, int i1, double v, String s, double v1, int i2, String s1, double v2, double v3) {

    }

    @Override
    public void orderStatus(int i, String s, Decimal decimal, Decimal decimal1, double v, int i1, int i2, double v1, int i3, String s1, double v2) {

    }

    @Override public void tickSnapshotEnd(int tickerId) {}

    @Override
    public void marketDataType(int i, int i1) {

    }

    @Override
    public void commissionReport(CommissionReport commissionReport) {

    }

    @Override
    public void position(String s, Contract contract, Decimal decimal, double v) {

    }

    @Override
    public void positionEnd() {

    }

    @Override public void currentTime(long time) {}

    @Override
    public void fundamentalData(int i, String s) {

    }

    @Override
    public void deltaNeutralValidation(int i, DeltaNeutralContract deltaNeutralContract) {

    }

    @Override public void contractDetails(int reqId, ContractDetails details) {}

    @Override
    public void bondContractDetails(int i, ContractDetails contractDetails) {

    }

    @Override public void contractDetailsEnd(int reqId) {}

    @Override
    public void execDetails(int i, Contract contract, Execution execution) {

    }

    @Override
    public void execDetailsEnd(int i) {

    }

    @Override
    public void updateMktDepth(int i, int i1, int i2, int i3, double v, Decimal decimal) {

    }

    @Override
    public void updateMktDepthL2(int i, int i1, String s, int i2, int i3, double v, Decimal decimal, boolean b) {

    }

    @Override
    public void updateNewsBulletin(int i, int i1, String s, String s1) {

    }

    @Override
    public void managedAccounts(String s) {

    }

    @Override
    public void receiveFA(int i, String s) {

    }

    @Override
    public void historicalData(int i, Bar bar) {

    }

    @Override
    public void scannerParameters(String s) {

    }

    @Override
    public void scannerData(int i, int i1, ContractDetails contractDetails, String s, String s1, String s2, String s3) {

    }

    @Override
    public void scannerDataEnd(int i) {

    }

    @Override
    public void realtimeBar(int i, long l, double v, double v1, double v2, double v3, Decimal decimal, Decimal decimal1, int i1) {

    }

    @Override public void nextValidId(int orderId) {}
    @Override public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {}
    @Override public void openOrderEnd() {}

    @Override
    public void updateAccountValue(String s, String s1, String s2, String s3) {

    }

    @Override
    public void updatePortfolio(Contract contract, Decimal decimal, double v, double v1, double v2, double v3, double v4, String s) {

    }

    @Override
    public void updateAccountTime(String s) {

    }

    @Override
    public void accountDownloadEnd(String s) {

    }

    @Override public void accountSummary(int reqId, String account, String tag, String value, String currency) {}

    @Override
    public void accountSummaryEnd(int i) {

    }

    @Override
    public void verifyMessageAPI(String s) {

    }

    @Override
    public void verifyCompleted(boolean b, String s) {

    }

    @Override
    public void verifyAndAuthMessageAPI(String s, String s1) {

    }

    @Override
    public void verifyAndAuthCompleted(boolean b, String s) {

    }

    @Override
    public void displayGroupList(int i, String s) {

    }

    @Override
    public void displayGroupUpdated(int i, String s) {

    }
}
