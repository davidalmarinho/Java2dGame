# *Asset Pool*:

### Summary:

- What is an Asset Pool? 

- Why are they so important?

- How an AssetPool looks like?

---

- ###### What is an Asset Pool?

An Asset Pool is a data structure that allows us to list all the resources that we had already loaded.

---

- ###### Why are they so important

They are very important, because, with an Asset Pool, we don't fall into the mistake of loading, for example, a 3D model 2 or 3 times, which will make our game heavier with no need.

---

- How an AssetPool looks like?

This is a game development project, so I will show you an example.

Most 2d games have spritesheets. Spritesheets are a group of sprites and each sprite is an animation of a game object.

<img title="Spritesheet" src="https://github.com/davidalmarinho/Java2dGame/blob/main/assets/spritesheet.png?raw=true" alt="Sorry, we couldn't load the image" data-align="center" width="258">

For example, in this image, we have sprites of Player, Enemy...

| Note | Each Player's image is a Sprite. |
| ---- | -------------------------------- |



Now, let's see the logic behind an AssetPool to make sure that this 'spritesheet.png' isn't loaded more than a time.

```mermaid
flowchart TB
    start(Init Game)--> loadSpritesheet[Load Spritesheet to the Game];
    loadSpritesheet --> assetPool[List it to AssetPool];
    assetPool--> alreadyLoaded{File Loaded Twice?};
    alreadyLoaded --> |No| playGame(Play Game);
    alreadyLoaded --> |Yes| warnDev[Throw Warning];
    warnDev --> playGame
```

So, at first, we launch the game.

Now, we will load a Spritesheet's file and at the same time, in an automatic way, we list the file in AssetPool's data structure.

If some spritesheet's file has loaded more than a time, we warn the developer, else we just keep running our program.

---

This was my explanation about AssetPool.

Thank you :D
