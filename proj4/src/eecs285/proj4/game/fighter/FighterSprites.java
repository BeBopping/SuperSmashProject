package eecs285.proj4.game.fighter;

import eecs285.proj4.util.SmallSprite;

// 55
public class FighterSprites {
	// 16 . 4 each
	public static final SmallSprite[] stand = {
		new SmallSprite(0.000f, 0.125f, 0.0f, 0.125f)};
	public static final SmallSprite[] jump = {
		new SmallSprite(0.125f, 0.250f, 0.0f, 0.125f)};
	public static final SmallSprite[] fall = {
		new SmallSprite(0.250f, 0.375f, 0.0f, 0.125f)};
	public static final SmallSprite[] stun = {
		new SmallSprite(0.375f, 0.500f, 0.0f, 0.125f)};
	public static final SmallSprite[] walk = {
		new SmallSprite(0.000f, 0.125f, 0.125f, 0.25f),
		new SmallSprite(0.125f, 0.250f, 0.125f, 0.25f),
		new SmallSprite(0.250f, 0.375f, 0.125f, 0.25f),
		new SmallSprite(0.375f, 0.500f, 0.125f, 0.25f),
		new SmallSprite(0.500f, 0.625f, 0.125f, 0.25f),
		new SmallSprite(0.625f, 0.750f, 0.125f, 0.25f),
		new SmallSprite(0.750f, 0.875f, 0.125f, 0.25f),
		new SmallSprite(0.875f, 1.000f, 0.125f, 0.25f)};

	// 12 . 3 each
	public static final SmallSprite[] normalAttackGround = {
		new SmallSprite(0.000f, 0.125f, 0.250f, 0.375f),
		new SmallSprite(0.000f, 0.125f, 0.375f, 0.500f),
		new SmallSprite(0.000f, 0.125f, 0.500f, 0.625f)};
	public static final SmallSprite[] normalAttackGroundUp = {
		new SmallSprite(0.000f, 0.125f, 0.625f, 0.750f),
		new SmallSprite(0.000f, 0.125f, 0.750f, 0.875f),
		new SmallSprite(0.000f, 0.125f, 0.875f, 1.000f)};
	public static final SmallSprite[] normalAttackGroundForward = {
		new SmallSprite(0.125f, 0.250f, 0.250f, 0.375f),
		new SmallSprite(0.125f, 0.250f, 0.375f, 0.500f),
		new SmallSprite(0.125f, 0.250f, 0.500f, 0.625f)};
	public static final SmallSprite[] normalAttackGroundDown = {
		new SmallSprite(0.125f, 0.250f, 0.625f, 0.750f),
		new SmallSprite(0.125f, 0.250f, 0.750f, 0.875f),
		new SmallSprite(0.125f, 0.250f, 0.875f, 1.000f)};

	// 15 . 3 each
	public static final SmallSprite[] normalAttackAir = {
		new SmallSprite(0.250f, 0.375f, 0.250f, 0.375f),
		new SmallSprite(0.250f, 0.375f, 0.375f, 0.500f),
		new SmallSprite(0.250f, 0.375f, 0.500f, 0.625f)};
	public static final SmallSprite[] normalAttackAirUp = {
		new SmallSprite(0.250f, 0.375f, 0.625f, 0.750f),
		new SmallSprite(0.250f, 0.375f, 0.750f, 0.875f),
		new SmallSprite(0.250f, 0.375f, 0.875f, 1.000f)};
	public static final SmallSprite[] normalAttackAirForward = {
		new SmallSprite(0.375f, 0.500f, 0.250f, 0.375f),
		new SmallSprite(0.375f, 0.500f, 0.375f, 0.500f),
		new SmallSprite(0.375f, 0.500f, 0.500f, 0.625f)};
	public static final SmallSprite[] normalAttackAirBackward = {
		new SmallSprite(0.375f, 0.500f, 0.625f, 0.750f),
		new SmallSprite(0.375f, 0.500f, 0.750f, 0.875f),
		new SmallSprite(0.375f, 0.500f, 0.875f, 1.000f)};
	public static final SmallSprite[] normalAttackAirDown = {
		new SmallSprite(0.500f, 0.625f, 0.250f, 0.375f),
		new SmallSprite(0.500f, 0.625f, 0.375f, 0.500f),
		new SmallSprite(0.500f, 0.625f, 0.500f, 0.625f)};

	// 12 . 3 each
	public static final SmallSprite[] specialAttack = {
		new SmallSprite(0.500f, 0.625f, 0.625f, 0.750f),
		new SmallSprite(0.500f, 0.625f, 0.750f, 0.875f),
		new SmallSprite(0.500f, 0.625f, 0.875f, 1.000f),
		new SmallSprite(0.625f, 0.750f, 0.250f, 0.375f)};
	public static final SmallSprite[] specialAttackUp = {
		new SmallSprite(0.625f, 0.750f, 0.375f, 0.500f),
		new SmallSprite(0.625f, 0.750f, 0.500f, 0.625f),
		new SmallSprite(0.625f, 0.750f, 0.625f, 0.750f),
		new SmallSprite(0.625f, 0.750f, 0.750f, 0.875f)};
	public static final SmallSprite[] specialAttackForward = {
		new SmallSprite(0.625f, 0.750f, 0.875f, 1.000f),
		new SmallSprite(0.750f, 0.875f, 0.250f, 0.375f),
		new SmallSprite(0.750f, 0.875f, 0.375f, 0.500f),
		new SmallSprite(0.750f, 0.875f, 0.500f, 0.625f)};
	public static final SmallSprite[] specialAttackDown = {
		new SmallSprite(0.750f, 0.875f, 0.625f, 0.750f),
		new SmallSprite(0.750f, 0.875f, 0.750f, 0.875f),
		new SmallSprite(0.750f, 0.875f, 0.875f, 1.000f),
		new SmallSprite(0.875f, 1.000f, 0.250f, 0.375f)};
}