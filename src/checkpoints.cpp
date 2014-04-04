// Copyright (c) 2009-2012 The Bitcoin developers
// Copyright (c) 2011-2012 Litecoin Developers
// Copyright (c) 2013 usde Developers

// Distributed under the MIT/X11 software license, see the accompanying
// file COPYING or http://www.opensource.org/licenses/mit-license.php.

#include <boost/assign/list_of.hpp> // for 'map_list_of()'
#include <boost/foreach.hpp>

#include "checkpoints.h"

#include "main.h"
#include "uint256.h"

namespace Checkpoints
{
    typedef std::map<int, uint256> MapCheckpoints;

    //
    // What makes a good checkpoint block?
    // + Is surrounded by blocks with reasonable timestamps
    //   (no blocks before with a timestamp after, none after with
    //    timestamp before)
    // + Contains no strange transactions
    //
    static MapCheckpoints mapCheckpoints =
            boost::assign::map_list_of
            ( 0, uint256("0x33abc26f9a026f1279cb49600efdd63f42e7c2d3a15463ad8090505d3e967752"))
			( 1, uint256("0xec9c4d88a04ede4cd777234ac504084c36cb25080c45b4741e2cfc0d5994359a"))
			( 50, uint256("0x253e145aae6b516ac47b9f6855675bea6f589922b74195cee77b31df1ebbc8c7"))
			( 3000, uint256("0xb0bf45beaad4446c666158baee04488267e622fabc49e6686b798ccd122018fe"))
			( 8000, uint256("0xde808d01865606385726824fd9f1466aacb94f233cd9713dc989333bcea15312"))
			( 10000, uint256("0xb5bab4cfa3e92985302a95afeb1b42755d6c240e73af61deb2599cb72aba991e"))
			( 20000, uint256("0x2f35019fbf04de7287aaa18b4010d2317779aac0a875183ff52934b8a3fee685"))
			( 135798, uint256("0xbd8423b7e21e1422953008db6ab7197b71b4cfabb9d9e69cc0cbcdcd7dd86b30"))
			;


    bool CheckBlock(int nHeight, const uint256& hash)
    {
        if (fTestNet) return true; // Testnet has no checkpoints

        MapCheckpoints::const_iterator i = mapCheckpoints.find(nHeight);
        if (i == mapCheckpoints.end()) return true;
        return hash == i->second;
    }

    int GetTotalBlocksEstimate()
    {
        if (fTestNet) return 0;
        return mapCheckpoints.rbegin()->first;
    }

    CBlockIndex* GetLastCheckpoint(const std::map<uint256, CBlockIndex*>& mapBlockIndex)
    {
        if (fTestNet) return NULL;

        BOOST_REVERSE_FOREACH(const MapCheckpoints::value_type& i, mapCheckpoints)
        {
            const uint256& hash = i.second;
            std::map<uint256, CBlockIndex*>::const_iterator t = mapBlockIndex.find(hash);
            if (t != mapBlockIndex.end())
                return t->second;
        }
        return NULL;
    }
}
