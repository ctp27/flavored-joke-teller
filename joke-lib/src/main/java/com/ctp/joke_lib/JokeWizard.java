package com.ctp.joke_lib;

import java.util.Random;

public class JokeWizard {

    private String[] jokes={
            "Wives are like grenades… – Remove the ring and boom, house is gone!",
            "My girlfriend accused me of cheating. I told her she was starting to sound like my wife.",
            "I lost my job at the bank on my very first day. – A woman asked me to check her balance, so I pushed her over.",
            "on a date me - \"I get to work with animals all day\"\n her - \"How sweet! What do you do?\" \nme - “I’m a butcher.”",
            "“Dad, how do stars die?” – “Usually an overdose.”",
            "Wife: “I look fat. Can you give me a compliment?” \nHusband: “You have perfect eyesight.”",
            "Today a man knocked on my door and asked for a small donation towards the local swimming pool. – I gave him a glass of water.",
            "Today was a terrible day. My ex got hit by a bus, and I lost my job as a bus driver",
            "An old teacher asked her student, “If I say, ‘I am beautiful,’ which tense is that?” The student replied, “It is obviously past.”",
            "If I had a dollar for every girl that found me unattractive, they would eventually find me attractive.",
            "Would you like to try African food??\n" + "\n" + "They would too.",
            "Wanna hear a terrible Joke?\n" + "\n" + "Paper\n" + "\n" + "Pretty tear-able, huh?",
            "My cow just wandered into a field of marijuana. The steaks have never been so high…",
            "What do you call a nun in a wheelchair?\n" + "\n" + "Virgin mobile.",
            "What did Stephen Hawking say when his computer crashed?\n" + "\n" + "Nothing.",
            "3.14% of sailors are…\n" + "\n" + "π-rates."
    };

    public String tellJoke(){
        Random random = new Random();
        return jokes[random.nextInt(jokes.length)];
    }
}
