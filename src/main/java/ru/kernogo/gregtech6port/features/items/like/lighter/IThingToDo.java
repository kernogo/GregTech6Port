package ru.kernogo.gregtech6port.features.items.like.lighter;

/** Thing to do for the lighter's logic */
interface IThingToDo {
    /** Runs lighter behavior, like place a fire / light a campfire / ignite a creeper / etc. */
    void lightAThingOnFire();

    /** Plays the lighter's sound */
    void playLighterSound();
}
