# _Bomberman_ en JavaFX

## Description

Ce projet fournit une implantation de base du jeu _Bomberman_ en _JavaFX_.
Pour pouvoir développer votre propre implantation de ce projet, vous devez
en créer une **divergence** en cliquant sur le bouton `Fork` en haut à droite
de cette page.

Lorsque ce sera fait, vous pourrez inviter les membres de votre groupe en tant
que _Developer_ pour vous permettre de travailler ensemble sur ce projet.

## Consignes

Vous pouvez retrouver ci-dessous les liens vers les sujets de TP vous guidant
dans le développement de votre projet.

- [Lancement du projet](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/r3-04/tp/-/tree/main/TP03)

## Diagramme de classes

```plantuml

top to bottom direction
skinparam linetype ortho

class AbstractMovable {
  # AbstractMovable(BombermanGame, double, double, Sprite): 
  # yPosition: DoubleProperty
  # sprite: ObjectProperty<Sprite>
  # xPosition: DoubleProperty
  # game: BombermanGame
  # horizontalSpeed: double
  # verticalSpeed: double
  - MARGIN: int
  # consumed: BooleanProperty
  + getY(): int
  + isOnWall(int, int): boolean
  + getWidth(): int
  + getHeight(): int
  + setVerticalSpeed(double): void
  + getXProperty(): DoubleProperty
  + isConsumed(): boolean
  + self(): IMovable
  + getGame(): BombermanGame
  + getSpriteProperty(): ObjectProperty<Sprite>
  + hashCode(): int
  + getX(): int
  + getVerticalSpeed(): double
  + getHorizontalSpeed(): double
  + setHorizontalSpeed(double): void
  + getSprite(): Sprite
  + equals(Object): boolean
  + isCollidingWith(IMovable): boolean
  + setSprite(Sprite): void
  + consume(): void
  + isConsumedProperty(): BooleanProperty
  + getYProperty(): DoubleProperty
  + setY(int): void
  + move(long): boolean
  + setX(int): void
}
class AddBombStrategy {
  + AddBombStrategy(IBombeStrategie): 
  ~ strategie: IBombeStrategie
  + applyBonus(Player): void
}
class AddBombStrategy {
  + AddBombStrategy(IBombeStrategie): 
  ~ strategie: IBombeStrategie
  + applyBonus(Player): void
}
class AddHpStrategy {
  + AddHpStrategy(): 
  + applyBonus(Player): void
}
class AddHpStrategy {
  + AddHpStrategy(): 
  + applyBonus(Player): void
}
class Bombe {
  + Bombe(BombermanGame, double, double, Sprite, IBombeStrategie): 
  # timeBombeDelay: long
  # timeDroppedBombe: long
  # strategie: IBombeStrategie
  # explosion: Explosion
  + hitBonus(IMovable, IBonusStrategy): void
  + collidedWith(IMovable): void
  + explode(): void
  - declencherExplosion(): void
  + hitEnemy(): void
  + setTimeDroppedBombe(long): void
  + move(long): boolean
}
class Bombe {
  + Bombe(BombermanGame, double, double, Sprite, IBombeStrategie): 
  # strategie: IBombeStrategie
  # timeDroppedBombe: long
  # timeBombeDelay: long
  # explosion: Explosion
  + move(long): boolean
  - declencherExplosion(): void
  + hitBonus(IMovable, IBonusStrategy): void
  + getTimeDroppedBombe(): long
  + explode(): void
  + hitEnemy(): void
  + setTimeDroppedBombe(long): void
  + collidedWith(IMovable): void
}
class Bomberman {
  + Bomberman(): 
  - GAME_WIDTH: int
  - NB_ENEMIES: int
  - GAME_HEIGHT: int
  + start(Stage): void
  + main(String[]): void
}
class BombermanAnimation {
  + BombermanAnimation(List<IMovable>): 
  - previousTimestamp: long
  - movableObjects: List<IMovable>
  - moveObjects(long): void
  - checkCollisions(): void
  + start(): void
  + handle(long): void
}
class BombermanController {
  + BombermanController(): 
  - started: boolean
  - bombs: Label
  - backgroundPane: GridPane
  - score: Label
  - stage: Stage
  - life: Label
  - game: BombermanGame
  - movingPane: Pane
  - message: Label
  + reset(): void
  + prepare(GameMap): void
  - addKeyListeners(): void
  + bindScore(IntegerExpression): void
  + bindBombs(IntegerExpression): void
  + addMovable(IMovable): void
  + gameOver(String): void
  + setStage(Stage): void
  + setGame(BombermanGame): void
  - createBackground(GameMap): void
  + bindLife(IntegerExpression): void
}
class BombermanGame {
  + BombermanGame(int, int, ISpriteStore, int): 
  - mapGenerator: IMapGenerator
  - height: int
  - controller: IBombermanController
  - levelBuilder: ILevelBuilder
  ~ rand: Random
  - nbEnemies: int
  + DEFAULT_SPEED: int
  - width: int
  - gameMap: GameMap
  - remainingEnemies: int
  + DEFAULT_BOMBS: int
  + RANDOM: Random
  - animation: AnimationTimer
  - player: Player
  - movableObjects: List<IMovable>
  - spriteStore: ISpriteStore
  + moveLeft(): void
  + getSpriteStore(): ISpriteStore
  + moveRight(): void
  + dropBomb(IMovable): void
  - spawnMovable(IMovable): void
  - initStatistics(): void
  + getGameMap(): GameMap
  + getCellAt(int, int): Cell
  + addMovable(IMovable): void
  - gameOver(String): void
  + moveUp(): void
  + getWidth(): int
  + playerIsDead(): void
  - createMovables(): void
  + getPlayer(): Player
  + enemyIsDead(IMovable): void
  + createMap(): GameMap
  + setMapGenerator(IMapGenerator): void
  + start(): void
  + setController(IBombermanController): void
  + moveDown(): void
  + prepare(): void
  + removeMovable(IMovable): void
  + stopMoving(): void
  + getHeight(): int
  + dropBomb(): void
  - clearAllMovables(): void
  - getCellOf(IMovable): Cell
}
class Bonus {
  + Bonus(BombermanGame, double, double, Sprite, IBonusStrategy): 
  ~ bonusStrategy: IBonusStrategy
  + hitBonus(IMovable, IBonusStrategy): void
  + hitEnemy(): void
  + explode(): void
  + collidedWith(IMovable): void
}
class CavalierExplosion {
  + CavalierExplosion(): 
  + explode(BombermanGame, double, double): void
}
class CavalierExplosion {
  + CavalierExplosion(): 
  + explode(BombermanGame, double, double): void
}
class Cell {
  + Cell(Sprite): 
  # Cell(Wall): 
  + Cell(int, int): 
  - spriteProperty: ObjectProperty<Sprite>
  - column: int
  - wallProperty: ObjectProperty<Wall>
  - row: int
  - random: Random
  + cellExplose(ISpriteStore, BombermanGame, double, double): void
  + getRow(): int
  + isEmpty(): boolean
  + getHeight(): int
  + getSprite(): Sprite
  + replaceBy(Cell): void
  + getColumn(): int
  + getSpriteProperty(): ObjectProperty<Sprite>
  + getWidth(): int
  + getWall(): Wall
  + getWallProperty(): ObjectProperty<Wall>
}
class DamagedWallState {
  + DamagedWallState(Sprite, SpriteStore): 
  - sprite: Sprite
  ~ spriteStore: SpriteStore
  + handleExplosion(Wall): void
  + getSprite(): Sprite
}
class DamagedWallState {
  + DamagedWallState(Sprite, SpriteStore): 
  - sprite: Sprite
  ~ spriteStore: SpriteStore
  + getSprite(): Sprite
  + handleExplosion(Wall): void
}
class DestroyedWallState {
  + DestroyedWallState(Sprite): 
  - sprite: Sprite
  + getSprite(): Sprite
  + handleExplosion(Wall): void
}
class DestroyedWallState {
  + DestroyedWallState(Sprite): 
  - sprite: Sprite
  + handleExplosion(Wall): void
  + getSprite(): Sprite
}
class DoubleExplosion {
  + DoubleExplosion(): 
  + explode(BombermanGame, double, double): void
}
class DoubleExplosion {
  + DoubleExplosion(): 
  + explode(BombermanGame, double, double): void
}
class Enemy {
  + Enemy(BombermanGame, double, double, Sprite, IEnemyStrategy): 
  ~ healthDecorator: MovableDecorator
  - strategy: IEnemyStrategy
  - isInvincible: BooleanProperty
  - INVINCIBILITY_DURATION_MS: int
  + hitEnemy(): void
  + move(long): boolean
  - triggerInvincibility(): void
  + collidedWith(IMovable): void
  + explode(): void
  + hitBonus(IMovable, IBonusStrategy): void
  + destroy(): void
}
class Enemy {
  + Enemy(BombermanGame, double, double, Sprite, IEnemyStrategy): 
  ~ healthDecorator: MovableDecorator
  - INVINCIBILITY_DURATION_MS: int
  - strategy: IEnemyStrategy
  - isInvincible: BooleanProperty
  + destroy(): void
  + explode(): void
  + move(long): boolean
  + hitEnemy(): void
  - triggerInvincibility(): void
  + hitBonus(IMovable, IBonusStrategy): void
  + collidedWith(IMovable): void
}
class Explosion {
  + Explosion(BombermanGame, double, double, Sprite, long): 
  # timeExplosionStart: long
  # timeExplosion: long
  ~ spriteStore: ISpriteStore
  + explode(): void
  + hitEnemy(): void
  + move(long): boolean
  ~ destroyWall(): void
  + hitBonus(IMovable, IBonusStrategy): void
  + collidedWith(IMovable): void
}
class GameMap {
  + GameMap(int, int): 
  - height: int
  - cells: Cell[][]
  - width: int
  + isOnMap(int, int): boolean
  + getWidth(): int
  + getHeight(): int
  - init(): void
  + getAt(int, int): Cell
  + getEmptyCells(): List<Cell>
  + setAt(int, int, Cell): void
}
class HealthDecorator {
  + HealthDecorator(IMovable, int): 
  - healthPoints: int
  + takeDamage(int): void
  + heal(int): void
  + hitBonus(IMovable, IBonusStrategy): void
  + getHealthPoints(): int
  + explode(): void
}
interface IBombeStrategie << interface >> {
  + explode(BombermanGame, double, double): void
}
interface IBombeStrategie << interface >> {
  + explode(BombermanGame, double, double): void
}
interface IBombermanController << interface >> {
  + addMovable(IMovable): void
  + reset(): void
  + prepare(GameMap): void
  + bindBombs(IntegerExpression): void
  + setGame(BombermanGame): void
  + bindScore(IntegerExpression): void
  + bindLife(IntegerExpression): void
  + gameOver(String): void
}
interface IBonusStrategy << interface >> {
  + applyBonus(Player): void
}
interface IBonusStrategy << interface >> {
  + applyBonus(Player): void
}
interface IEnemyStrategy << interface >> {
  + move(long, BombermanGame, Enemy, boolean): boolean
}
interface IEnemyStrategy << interface >> {
  + move(long, BombermanGame, Enemy, boolean): boolean
}
interface ILevelBuilder << interface >> {
  + getMap(): IMapGenerator
  + getNumberOfEnnemy(): int
  + getEnemyStrategy(): IEnemyStrategy
  + getNextLevelBuilder(): ILevelBuilder
}
interface IMapGenerator << interface >> {
  + generateMap(int, int): GameMap
}
interface IMovable << interface >> {
  + consume(): void
  + getY(): int
  + getVerticalSpeed(): double
  + getSpriteProperty(): ObjectProperty<Sprite>
  + getX(): int
  + getYProperty(): DoubleProperty
  + self(): IMovable
  + isConsumedProperty(): BooleanProperty
  + getHeight(): int
  + getXProperty(): DoubleProperty
  + setX(int): void
  + isCollidingWith(IMovable): boolean
  + hitEnemy(): void
  + setY(int): void
  + getSprite(): Sprite
  + isConsumed(): boolean
  + setVerticalSpeed(double): void
  + setSprite(Sprite): void
  + collidedWith(IMovable): void
  + getWidth(): int
  + move(long): boolean
  + explode(): void
  + hitBonus(IMovable, IBonusStrategy): void
  + getHorizontalSpeed(): double
  + setHorizontalSpeed(double): void
}
interface IPlayerState << interface >> {
  + hit(Player): void
}
interface ISpriteStore << interface >> {
  + getSpriteSize(): int
  + getSprite(String): Sprite
}
interface IWallState << interface >> {
  + handleExplosion(Wall): void
  + getSprite(): Sprite
}
class IntactWallState {
  + IntactWallState(Sprite, SpriteStore): 
  ~ spriteStore: SpriteStore
  - sprite: Sprite
  + handleExplosion(Wall): void
  + getSprite(): Sprite
}
class IntactWallState {
  + IntactWallState(Sprite, SpriteStore): 
  - sprite: Sprite
  ~ spriteStore: SpriteStore
  + getSprite(): Sprite
  + handleExplosion(Wall): void
}
class InvincibilityStrategy {
  + InvincibilityStrategy(): 
  + applyBonus(Player): void
}
class InvincibilityStrategy {
  + InvincibilityStrategy(): 
  + applyBonus(Player): void
}
class InvisibleState {
  + InvisibleState(Player): 
  - INVISIBILITY_DURATION: int
  + hit(Player): void
  - startInvisibilityTimer(Player): void
}
class LevelOne {
  + LevelOne(): 
  + getEnemyStrategy(): IEnemyStrategy
  + getNumberOfEnnemy(): int
  + getNextLevelBuilder(): ILevelBuilder
  + getMap(): IMapGenerator
}
class LevelOne {
  + LevelOne(): 
  + getMap(): IMapGenerator
  + getNumberOfEnnemy(): int
  + getEnemyStrategy(): IEnemyStrategy
  + getNextLevelBuilder(): ILevelBuilder
}
class LevelTwo {
  + LevelTwo(): 
  + getNextLevelBuilder(): ILevelBuilder
  + getMap(): IMapGenerator
  + getNumberOfEnnemy(): int
  + getEnemyStrategy(): IEnemyStrategy
}
class LevelTwo {
  + LevelTwo(): 
  + getMap(): IMapGenerator
  + getNextLevelBuilder(): ILevelBuilder
  + getNumberOfEnnemy(): int
  + getEnemyStrategy(): IEnemyStrategy
}
class MapDeux {
  + MapDeux(SpriteStore): 
  - spriteStore: SpriteStore
  + generateMap(int, int): GameMap
}
class MapDeux {
  + MapDeux(SpriteStore): 
  ~ random: Random
  - spriteStore: SpriteStore
  + generateMap(int, int): GameMap
}
class MapGenerator {
  + MapGenerator(SpriteStore, IMapGenerator): 
  - spriteStore: SpriteStore
  - mapGenerator: IMapGenerator
  + generateMap(int, int): GameMap
}
class MapUne {
  + MapUne(SpriteStore): 
  - spriteStore: SpriteStore
  + generateMap(int, int): GameMap
}
class MapUne {
  + MapUne(SpriteStore): 
  - spriteStore: SpriteStore
  + generateMap(int, int): GameMap
}
class MineExplosion {
  + MineExplosion(): 
  - estEnclenche: boolean
  + explode(BombermanGame, double, double): void
}
class MineExplosion {
  + MineExplosion(): 
  - estEnclenche: boolean
  + explode(BombermanGame, double, double): void
}
class MirrorPlayerMovementsStrategy {
  + MirrorPlayerMovementsStrategy(): 
  + move(long, BombermanGame, Enemy, boolean): boolean
}
class MirrorPlayerMovementsStrategy {
  + MirrorPlayerMovementsStrategy(): 
  + move(long, BombermanGame, Enemy, boolean): boolean
}
class MovableDecorator {
  + MovableDecorator(IMovable): 
  # decoratedMovable: IMovable
  + getY(): int
  + consume(): void
  + collidedWith(IMovable): void
  + heal(int): void
  + equals(Object): boolean
  + getHeight(): int
  + getWidth(): int
  + hitEnemy(): void
  + getSprite(): Sprite
  + getYProperty(): DoubleProperty
  + setX(int): void
  + setVerticalSpeed(double): void
  + getHealthPoints(): int
  + setY(int): void
  + takeDamage(int): void
  + getHorizontalSpeed(): double
  + getX(): int
  + isConsumedProperty(): BooleanProperty
  + setSprite(Sprite): void
  + isConsumed(): boolean
  + setHorizontalSpeed(double): void
  + isCollidingWith(IMovable): boolean
  + hashCode(): int
  + getSpriteProperty(): ObjectProperty<Sprite>
  + explode(): void
  + getXProperty(): DoubleProperty
  + move(long): boolean
  + getVerticalSpeed(): double
  + self(): IMovable
}
class NormalExplosion {
  + NormalExplosion(): 
  + explode(BombermanGame, double, double): void
}
class NormalExplosion {
  + NormalExplosion(): 
  + explode(BombermanGame, double, double): void
}
class NormalState {
  + NormalState(): 
  + hit(Player): void
}
class NukeExplosion {
  + NukeExplosion(): 
  - BOOM: String
  + explode(BombermanGame, double, double): void
}
class NukeExplosion {
  + NukeExplosion(): 
  - BOOM: String
  + explode(BombermanGame, double, double): void
}
class Player {
  # Player(BombermanGame, double, double, Sprite): 
  ~ MAXLIVES: int
  - lives: IntegerProperty
  - healthDecorator: MovableDecorator
  - bombes: ObservableList<Bombe>
  - state: iPlayerState
  - score: IntegerProperty
  + setState(iPlayerState): void
  + livesProperty(): IntegerProperty
  + getBombes(): ObservableList<Bombe>
  + getLives(): int
  + explode(): void
  + hitBonus(IMovable, IBonusStrategy): void
  + hitEnemy(): void
  + decreaseLives(): void
  + collidedWith(IMovable): void
  + increaseLives(int): void
  + hit(): void
  + addBomb(Bombe): void
  + getScore(): int
  + increaseScore(): void
  + scoreProperty(): IntegerProperty
}
class RandomMapGenerator {
  + RandomMapGenerator(SpriteStore): 
  - spriteStore: SpriteStore
  - random: Random
  + generateMap(int, int): GameMap
}
class RandomMovesStrategy {
  + RandomMovesStrategy(): 
  ~ rand: Random
  + move(long, BombermanGame, Enemy, boolean): boolean
}
class RandomMovesStrategy {
  + RandomMovesStrategy(): 
  ~ rand: Random
  + move(long, BombermanGame, Enemy, boolean): boolean
}
class Sprite {
  + Sprite(Image): 
  - image: Image
  + getImage(): Image
  + draw(GraphicsContext, int, int): void
  + getHeight(): int
  + getWidth(): int
}
class SpriteStore {
  - SpriteStore(): 
  - spriteCache: Map<String, Sprite>
  ~ INSTANCE: SpriteStore
  + getSprite(String): Sprite
  + getInstance(): SpriteStore
  - loadImage(String): Image
}
class StraightLineStrategy {
  + StraightLineStrategy(): 
  + move(long, BombermanGame, Enemy, boolean): boolean
}
class StraightLineStrategy {
  + StraightLineStrategy(): 
  + move(long, BombermanGame, Enemy, boolean): boolean
}
class UnbreakableWallState {
  + UnbreakableWallState(Sprite): 
  ~ sprite: Sprite
  + getSprite(): Sprite
  + handleExplosion(Wall): void
}
class UnbreakableWallState {
  + UnbreakableWallState(Sprite): 
  ~ sprite: Sprite
  + handleExplosion(Wall): void
  + getSprite(): Sprite
}
class Wall {
  + Wall(Sprite, SpriteStore, boolean): 
  - state: IWallState
  - isDestroyed: boolean
  + setState(IWallState): void
  + isDestroyed(): boolean
  + handleExplosion(): void
  + getSprite(): Sprite
  + setDestroyed(boolean): void
}
class build {
  + build(): 
  + run(): Object
  + getMetaClass(): MetaClass
  + main(String[]): void
  + invokeMethod(String, Object): Object
  + setProperty(String, Object): void
  + getProperty(String): Object
  + setMetaClass(MetaClass): void
}
interface iPlayerState << interface >> {
  + hit(Player): void
}
class settings {
  + settings(): 
  + main(String[]): void
  + getProperty(String): Object
  + getMetaClass(): MetaClass
  + setProperty(String, Object): void
  + run(): Object
  + invokeMethod(String, Object): Object
  + setMetaClass(MetaClass): void
}

AbstractMovable               "1" *-[#595959,plain]-> "game\n1" BombermanGame                 
AbstractMovable                -[#008200,dashed]-^  IMovable                      
AbstractMovable                -[#595959,dashed]->  Sprite                        
AddBombStrategy                -[#595959,dashed]->  AbstractMovable               
AddBombStrategy                -[#595959,dashed]->  Bombe                         : "«create»"
AddBombStrategy               "1" *-[#595959,plain]-> "strategie\n1" IBombeStrategie               
AddBombStrategy                -[#008200,dashed]-^  IBonusStrategy                
AddBombStrategy                -[#595959,dashed]->  Player                        
AddBombStrategy                -[#595959,dashed]->  Player                        
AddBombStrategy                -[#595959,dashed]->  SpriteStore                   
AddHpStrategy                  -[#008200,dashed]-^  IBonusStrategy                
AddHpStrategy                  -[#595959,dashed]->  Player                        
AddHpStrategy                  -[#595959,dashed]->  Player                        
Bombe                          -[#000082,plain]-^  AbstractMovable               
Bombe                          -[#000082,plain]-^  AbstractMovable               
Bombe                          -[#595959,dashed]->  Bombe                         
Bombe                          -[#595959,dashed]->  BombermanGame                 
Bombe                          -[#595959,dashed]->  BombermanGame                 
Bombe                         "1" *-[#595959,plain]-> "explosion\n1" Explosion                     
Bombe                         "1" *-[#595959,plain]-> "explosion\n1" Explosion                     
Bombe                         "1" *-[#595959,plain]-> "strategie\n1" IBombeStrategie               
Bombe                          -[#595959,dashed]->  IBonusStrategy                
Bombe                          -[#595959,dashed]->  IMovable                      
Bombe                          -[#595959,dashed]->  IMovable                      
Bombe                          -[#595959,dashed]->  ISpriteStore                  
Bombe                          -[#595959,dashed]->  Sprite                        
Bombe                          -[#595959,dashed]->  Sprite                        
BombermanAnimation            "1" *-[#595959,plain]-> "movableObjects\n*" IMovable                      
BombermanController           "1" *-[#595959,plain]-> "game\n1" BombermanGame                 
BombermanController            -[#595959,dashed]->  GameMap                       
BombermanController            -[#008200,dashed]-^  IBombermanController          
BombermanController            -[#595959,dashed]->  IMovable                      
BombermanGame                  -[#595959,dashed]->  Cell                          
BombermanGame                 "1" *-[#595959,plain]-> "gameMap\n1" GameMap                       
BombermanGame                 "1" *-[#595959,plain]-> "controller\n1" IBombermanController          
BombermanGame                 "1" *-[#595959,plain]-> "levelBuilder\n1" ILevelBuilder                 
BombermanGame                 "1" *-[#595959,plain]-> "mapGenerator\n1" IMapGenerator                 
BombermanGame                 "1" *-[#595959,plain]-> "movableObjects\n*" IMovable                      
BombermanGame                 "1" *-[#595959,plain]-> "spriteStore\n1" ISpriteStore                  
BombermanGame                 "1" *-[#595959,plain]-> "player\n1" Player                        
Bonus                          -[#000082,plain]-^  AbstractMovable               
Bonus                          -[#595959,dashed]->  BombermanGame                 
Bonus                          -[#595959,dashed]->  IMovable                      
Bonus                          -[#595959,dashed]->  Sprite                        
CavalierExplosion              -[#595959,dashed]->  BombermanGame                 
CavalierExplosion              -[#595959,dashed]->  BombermanGame                 
CavalierExplosion              -[#595959,dashed]->  Explosion                     : "«create»"
CavalierExplosion              -[#008200,dashed]-^  IBombeStrategie               
CavalierExplosion              -[#595959,dashed]->  ISpriteStore                  
Cell                           -[#595959,dashed]->  BombermanGame                 
Cell                           -[#595959,dashed]->  Cell                          
Cell                           -[#595959,dashed]->  ISpriteStore                  
Cell                           -[#595959,dashed]->  Sprite                        
Cell                           -[#595959,dashed]->  Wall                          
DamagedWallState               -[#595959,dashed]->  DestroyedWallState            : "«create»"
DamagedWallState               -[#008200,dashed]-^  IWallState                    
DamagedWallState               -[#008200,dashed]-^  IWallState                    
DamagedWallState              "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
DamagedWallState              "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
DamagedWallState              "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
DamagedWallState              "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
DamagedWallState               -[#595959,dashed]->  Wall                          
DamagedWallState               -[#595959,dashed]->  Wall                          
DestroyedWallState             -[#008200,dashed]-^  IWallState                    
DestroyedWallState             -[#008200,dashed]-^  IWallState                    
DestroyedWallState            "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
DestroyedWallState            "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
DestroyedWallState             -[#595959,dashed]->  Wall                          
DestroyedWallState             -[#595959,dashed]->  Wall                          
DoubleExplosion                -[#595959,dashed]->  BombermanGame                 
DoubleExplosion                -[#595959,dashed]->  BombermanGame                 
DoubleExplosion                -[#595959,dashed]->  Explosion                     : "«create»"
DoubleExplosion                -[#008200,dashed]-^  IBombeStrategie               
DoubleExplosion                -[#595959,dashed]->  ISpriteStore                  
Enemy                          -[#000082,plain]-^  AbstractMovable               
Enemy                          -[#000082,plain]-^  AbstractMovable               
Enemy                          -[#595959,dashed]->  BombermanGame                 
Enemy                          -[#595959,dashed]->  BombermanGame                 
Enemy                          -[#595959,dashed]->  Enemy                         
Enemy                          -[#595959,dashed]->  HealthDecorator               : "«create»"
Enemy                          -[#595959,dashed]->  IBonusStrategy                
Enemy                         "1" *-[#595959,plain]-> "strategy\n1" IEnemyStrategy                
Enemy                          -[#595959,dashed]->  IMovable                      
Enemy                          -[#595959,dashed]->  IMovable                      
Enemy                         "1" *-[#595959,plain]-> "healthDecorator\n1" MovableDecorator              
Enemy                         "1" *-[#595959,plain]-> "healthDecorator\n1" MovableDecorator              
Enemy                          -[#595959,dashed]->  Sprite                        
Enemy                          -[#595959,dashed]->  Sprite                        
Explosion                      -[#000082,plain]-^  AbstractMovable               
Explosion                      -[#595959,dashed]->  BombermanGame                 
Explosion                      -[#595959,dashed]->  IMovable                      
Explosion                     "1" *-[#595959,plain]-> "spriteStore\n1" ISpriteStore                  
Explosion                      -[#595959,dashed]->  Sprite                        
GameMap                       "1" *-[#595959,plain]-> "cells\n*" Cell                          
HealthDecorator                -[#595959,dashed]->  IMovable                      
HealthDecorator                -[#000082,plain]-^  MovableDecorator              
IBombeStrategie                -[#595959,dashed]->  BombermanGame                 
IBombeStrategie                -[#595959,dashed]->  BombermanGame                 
IBombermanController           -[#595959,dashed]->  BombermanGame                 
IBombermanController           -[#595959,dashed]->  GameMap                       
IBombermanController           -[#595959,dashed]->  IMovable                      
IBonusStrategy                 -[#595959,dashed]->  Player                        
IBonusStrategy                 -[#595959,dashed]->  Player                        
IEnemyStrategy                 -[#595959,dashed]->  BombermanGame                 
IEnemyStrategy                 -[#595959,dashed]->  BombermanGame                 
IEnemyStrategy                 -[#595959,dashed]->  Enemy                         
ILevelBuilder                  -[#595959,dashed]->  ILevelBuilder                 
ILevelBuilder                  -[#595959,dashed]->  IMapGenerator                 
IMapGenerator                  -[#595959,dashed]->  GameMap                       
IMovable                       -[#595959,dashed]->  IMovable                      
IMovable                       -[#595959,dashed]->  Sprite                        
IPlayerState                   -[#595959,dashed]->  Player                        
ISpriteStore                   -[#595959,dashed]->  Sprite                        
IWallState                     -[#595959,dashed]->  Sprite                        
IWallState                     -[#595959,dashed]->  Wall                          
IntactWallState                -[#595959,dashed]->  DamagedWallState              : "«create»"
IntactWallState                -[#008200,dashed]-^  IWallState                    
IntactWallState                -[#008200,dashed]-^  IWallState                    
IntactWallState               "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
IntactWallState               "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
IntactWallState               "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
IntactWallState               "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
IntactWallState                -[#595959,dashed]->  Wall                          
IntactWallState                -[#595959,dashed]->  Wall                          
InvincibilityStrategy          -[#008200,dashed]-^  IBonusStrategy                
InvincibilityStrategy          -[#595959,dashed]->  InvisibleState                : "«create»"
InvincibilityStrategy          -[#595959,dashed]->  Player                        
InvincibilityStrategy          -[#595959,dashed]->  Player                        
InvisibleState                 -[#595959,dashed]->  Player                        
LevelOne                       -[#595959,dashed]->  IEnemyStrategy                
LevelOne                       -[#008200,dashed]-^  ILevelBuilder                 
LevelOne                       -[#008200,dashed]-^  ILevelBuilder                 
LevelOne                       -[#595959,dashed]->  IMapGenerator                 
LevelOne                       -[#595959,dashed]->  IMapGenerator                 
LevelOne                       -[#595959,dashed]->  LevelTwo                      : "«create»"
LevelOne                       -[#595959,dashed]->  MapUne                        : "«create»"
LevelOne                       -[#595959,dashed]->  SpriteStore                   
LevelOne                       -[#595959,dashed]->  StraightLineStrategy          : "«create»"
LevelTwo                       -[#595959,dashed]->  IEnemyStrategy                
LevelTwo                       -[#008200,dashed]-^  ILevelBuilder                 
LevelTwo                       -[#008200,dashed]-^  ILevelBuilder                 
LevelTwo                       -[#595959,dashed]->  IMapGenerator                 
LevelTwo                       -[#595959,dashed]->  IMapGenerator                 
LevelTwo                       -[#595959,dashed]->  MapDeux                       : "«create»"
LevelTwo                       -[#595959,dashed]->  RandomMovesStrategy           : "«create»"
LevelTwo                       -[#595959,dashed]->  SpriteStore                   
MapDeux                        -[#595959,dashed]->  Cell                          : "«create»"
MapDeux                        -[#595959,dashed]->  DamagedWallState              : "«create»"
MapDeux                        -[#595959,dashed]->  DestroyedWallState            : "«create»"
MapDeux                        -[#595959,dashed]->  GameMap                       
MapDeux                        -[#595959,dashed]->  GameMap                       : "«create»"
MapDeux                        -[#008200,dashed]-^  IMapGenerator                 
MapDeux                        -[#008200,dashed]-^  IMapGenerator                 
MapDeux                        -[#595959,dashed]->  Sprite                        
MapDeux                       "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
MapDeux                       "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
MapDeux                        -[#595959,dashed]->  Wall                          : "«create»"
MapGenerator                   -[#595959,dashed]->  GameMap                       
MapGenerator                   -[#008200,dashed]-^  IMapGenerator                 
MapGenerator                  "1" *-[#595959,plain]-> "mapGenerator\n1" IMapGenerator                 
MapGenerator                  "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
MapUne                         -[#595959,dashed]->  Cell                          : "«create»"
MapUne                         -[#595959,dashed]->  GameMap                       : "«create»"
MapUne                         -[#595959,dashed]->  GameMap                       
MapUne                         -[#008200,dashed]-^  IMapGenerator                 
MapUne                         -[#008200,dashed]-^  IMapGenerator                 
MapUne                         -[#595959,dashed]->  Sprite                        
MapUne                        "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
MapUne                        "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
MapUne                         -[#595959,dashed]->  Wall                          : "«create»"
MineExplosion                  -[#595959,dashed]->  BombermanGame                 
MineExplosion                  -[#595959,dashed]->  BombermanGame                 
MineExplosion                  -[#595959,dashed]->  Explosion                     : "«create»"
MineExplosion                  -[#008200,dashed]-^  IBombeStrategie               
MineExplosion                  -[#595959,dashed]->  ISpriteStore                  
MirrorPlayerMovementsStrategy  -[#595959,dashed]->  AbstractMovable               
MirrorPlayerMovementsStrategy  -[#595959,dashed]->  BombermanGame                 
MirrorPlayerMovementsStrategy  -[#595959,dashed]->  BombermanGame                 
MirrorPlayerMovementsStrategy  -[#595959,dashed]->  Enemy                         
MirrorPlayerMovementsStrategy  -[#008200,dashed]-^  IEnemyStrategy                
MovableDecorator               -[#008200,dashed]-^  IMovable                      
MovableDecorator              "1" *-[#595959,plain]-> "decoratedMovable\n1" IMovable                      
MovableDecorator               -[#595959,dashed]->  Sprite                        
NormalExplosion                -[#595959,dashed]->  BombermanGame                 
NormalExplosion                -[#595959,dashed]->  BombermanGame                 
NormalExplosion                -[#595959,dashed]->  Explosion                     : "«create»"
NormalExplosion                -[#008200,dashed]-^  IBombeStrategie               
NormalExplosion                -[#595959,dashed]->  ISpriteStore                  
NormalState                    -[#595959,dashed]->  Player                        
NukeExplosion                  -[#595959,dashed]->  BombermanGame                 
NukeExplosion                  -[#595959,dashed]->  BombermanGame                 
NukeExplosion                  -[#595959,dashed]->  Explosion                     : "«create»"
NukeExplosion                  -[#595959,dashed]->  GameMap                       
NukeExplosion                  -[#008200,dashed]-^  IBombeStrategie               
NukeExplosion                  -[#595959,dashed]->  ISpriteStore                  
Player                         -[#000082,plain]-^  AbstractMovable               
Player                         -[#595959,dashed]->  BombermanGame                 
Player                         -[#595959,dashed]->  IMovable                      
Player                        "1" *-[#595959,plain]-> "healthDecorator\n1" MovableDecorator              
Player                         -[#595959,dashed]->  Sprite                        
RandomMapGenerator             -[#595959,dashed]->  GameMap                       
RandomMapGenerator             -[#008200,dashed]-^  IMapGenerator                 
RandomMapGenerator            "1" *-[#595959,plain]-> "spriteStore\n1" SpriteStore                   
RandomMovesStrategy            -[#595959,dashed]->  AbstractMovable               
RandomMovesStrategy            -[#595959,dashed]->  BombermanGame                 
RandomMovesStrategy            -[#595959,dashed]->  BombermanGame                 
RandomMovesStrategy            -[#595959,dashed]->  Enemy                         
RandomMovesStrategy            -[#008200,dashed]-^  IEnemyStrategy                
SpriteStore                    -[#008200,dashed]-^  ISpriteStore                  
SpriteStore                   "1" *-[#595959,plain]-> "spriteCache\n*" Sprite                        
SpriteStore                   "1" *-[#595959,plain]-> "INSTANCE\n1" SpriteStore                   
StraightLineStrategy           -[#595959,dashed]->  AbstractMovable               
StraightLineStrategy           -[#595959,dashed]->  BombermanGame                 
StraightLineStrategy           -[#595959,dashed]->  BombermanGame                 
StraightLineStrategy           -[#595959,dashed]->  Enemy                         
StraightLineStrategy           -[#008200,dashed]-^  IEnemyStrategy                
UnbreakableWallState           -[#008200,dashed]-^  IWallState                    
UnbreakableWallState           -[#008200,dashed]-^  IWallState                    
UnbreakableWallState          "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
UnbreakableWallState          "1" *-[#595959,plain]-> "sprite\n1" Sprite                        
UnbreakableWallState           -[#595959,dashed]->  Wall                          
UnbreakableWallState           -[#595959,dashed]->  Wall                          
Wall                          "1" *-[#595959,plain]-> "state\n1" IWallState                    
Wall                           -[#595959,dashed]->  Sprite                        
Wall                           -[#595959,dashed]->  SpriteStore                   
iPlayerState                   -[#595959,dashed]->  Player                        
```

## Tâches réalisées

### TP n°3

| Fonctionnalité                         | Terminée ? | Auteur(s)       |
| -------------------------------------- | ---------- | --------------- |
| Représentation des ennemis             | Oui        | Romain Lechêne  |
| Intégration des ennemis dans la partie | Oui        | Romain Lechêne  |
| Représentation du joueur               | Oui        | Mattéo Fierquin |
| Intégration du joueur dans la partie   | Oui        | Mattéo Fierquin |
| Représentation des bombes et explosion | Oui        | Théo Delaude    |
| Intégration des bombes dans la partie  | Oui        | Théo Delaude    |
| Création de la carte du jeu            | Oui        | Théo Mortreux   |

### TP n°4

| Fonctionnalité                              | Patron de conception utilisé | Terminée ? | Auteur(s)         |
| ------------------------------------------- | ---------------------------- | ---------- | ------------------|
| Variantes de déplacement des ennemis        | Stratégie                    | Oui        | Romain            |
| Gestion des points de vie (ennemis, joueur) | Décorateur                   | Oui        | Mattéo            |
| Invulnérabilité du joueur                   | Etat                         | Oui        | Mattéo            |
| Solidité des murs                           | Etat                         | Oui        | Théo M. / Mattéo  |
| Variantes de génération pour la carte       | Stratégie                    | Oui        | Théo M.           |
| Différents types de bombes                  | Stratégie                    | Oui        | Théo D.           |

### TP n°5

| Fonctionnalité                              | Patron de conception utilisé | Terminée ? | Auteur(s)                                     |
| ------------------------------------------- | ---------------------------- | ---------- | --------------------------------------------- |
| Instance du `SpriteStore`                   | Singleton                    | Oui           | Mattéo Fierquin                               |
| Bonus de bombe                              | Stratégie                    | Oui           | Théo Delaude                                             |
| Bonus d'invulnérabilité                     | Stratégie                    | Oui           | Romain / Mattéo                                              |
| Bonus de point de vie                       | Stratégie                    | Oui           | Mattéo Fierquin                                              |
| Apparition aléatoire d'un bonus             | /                            | Oui           |  Théo D. / Romain                                            |
| Gestion des différents niveaux              | Fabrique abstraite           | Oui           | Théo M. / Mattéo                                              |
