package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "USDe";
    public static final String coinTicker = "USDE";
    public static final String coinURIScheme = "usde";
    public static final String cryptsyMarketId = "139";
    public static final String cryptsyMarketCurrency = "BTC";


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://cryptexplorer.com/";
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://cryptexplorer.com/";

    public static final String DONATION_ADDRESS = "GWyBh83CPBKNoVKHwkarzR9usGAsLpSJPk";  //Scottlle aka xploited

    enum CoinHash {
        SHA256,
        scrypt
    };
    public static final CoinHash coinHash = CoinHash.scrypt;
    //Original Values
    public static final int TARGET_TIMESPAN = (int)(2 * 60 * 60);  // USDE: 2 hours
    public static final int TARGET_SPACING = (int)(1 * 60);  // USDE: 60 seconds
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  

    public static int spendableCoinbaseDepth = 100; //main.h: static const int COINBASE_MATURITY
    //public static final int MAX_MONEY = 1600000000;                 //main.h:  MAX_MONEY
    public static final String MAX_MONEY_STRING = "1600000000";     //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(10000000);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = Utils.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 60002;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION

    public static final boolean supportsBloomFiltering = false; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 54449;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 44449;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 38;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS

    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xd9d9f9bd;      // 0xd9, 0xd9, 0xf9, 0xbd

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1365048244L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (134453);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "33abc26f9a026f1279cb49600efdd63f42e7c2d3a15463ad8090505d3e967752"; //main.cpp: hashGenesisBlock 1f42509b6d35a6aa60af4ec9b98d8ce4ffbe46c076d4c2da933e87550ab775f2 
                                                             //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "04ffff001d0104155553444520666f756e64656420312f312f32303134";   //"Digitalcoin, A Currency for a Digital Age"
    static public String genessiXOutBytes = "a5814813115273a109cff99907ba4a05d951873dae7acb6c973d0c9e7c88911a3dbc9aa600deac241b91707e7b4ffb30ad91c8e56e695a1ddf318592988afe0a";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
              "liteminers.com" //"seed2.hashdragon.com",
    };

    //
    // TestNet - usde - not tested or setup
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0xfcc1b7dc;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 999999L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (99999);                         //main.cpp: LoadBlockIndex


    //main.cpp GetBlockValue(height, fee)
    public static final BigInteger GetBlockReward(int height)
    {
         int COIN = 1;
        BigInteger nSubsidy = Utils.toNanoCoins(1000, 0);

        if((height % 100) == 1)
        {
            nSubsidy = Utils.toNanoCoins(100000, 0); //2
        }
        else if((height % 50) == 1)
        {
            nSubsidy   = Utils.toNanoCoins(50000, 0); //2
        }
        else if((height % 20) == 1)
        {
            nSubsidy   = Utils.toNanoCoins(20000, 0); //2
        }
        else if((height % 10) == 1)
        {
            nSubsidy   = Utils.toNanoCoins(10000, 0); //2
        }
        else if((height % 5) == 1)
        {
            nSubsidy   = Utils.toNanoCoins(5000, 0); //2
        }
        
				if (height < 2){
						nSubsidy   = Utils.toNanoCoins(24000000, 0); //2
				}else if(height < 500)
				{
						nSubsidy   = Utils.toNanoCoins(100, 0); //2
				}
				else if(height < 1000)  
				{
						nSubsidy   = Utils.toNanoCoins(500, 0); //2
				}
        
        
        
        nSubsidy = nSubsidy.shiftRight(height /  139604);
       
        return nSubsidy;
    }

    public static int subsidyDecreaseBlockCount = 139604;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // usde: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "04A9CFD81AF5D53310BE45E6326E706A542B1028DF85D2819D5DE496D8816C83129CE874FE5E3A23B03544BFF35458833779DAB7A6FF687525A4E23CA59F1E2B94";
    public static final String TESTNET_SATOSHI_KEY = "";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.bitcoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.bitcoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.bitcoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
			checkpoints.put( 0, new Sha256Hash("33abc26f9a026f1279cb49600efdd63f42e7c2d3a15463ad8090505d3e967752"));
			checkpoints.put( 1, new Sha256Hash("ec9c4d88a04ede4cd777234ac504084c36cb25080c45b4741e2cfc0d5994359a"));
			checkpoints.put( 50, new Sha256Hash("253e145aae6b516ac47b9f6855675bea6f589922b74195cee77b31df1ebbc8c7"));
			checkpoints.put( 3000, new Sha256Hash("b0bf45beaad4446c666158baee04488267e622fabc49e6686b798ccd122018fe"));
			checkpoints.put( 8000, new Sha256Hash("de808d01865606385726824fd9f1466aacb94f233cd9713dc989333bcea15312"));
			checkpoints.put( 10000, new Sha256Hash("b5bab4cfa3e92985302a95afeb1b42755d6c240e73af61deb2599cb72aba991e"));
			checkpoints.put( 20000, new Sha256Hash("2f35019fbf04de7287aaa18b4010d2317779aac0a875183ff52934b8a3fee685"));
    }


}
