package com.cheatbreaker.client.event.data;

import com.cheatbreaker.bridge.ref.Ref;
import com.cheatbreaker.main.CheatBreaker;
import com.cheatbreaker.main.identification.MinecraftVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KeyObject {
    NONE(0 /* Keyboard.KEY_NONE */, -1 /* GLFW.GLFW_KEY_UNKNOWN */),
    ESCAPE(1 /* Keyboard.KEY_ESCAPE */, 256 /* GLFW.GLFW_KEY_ESCAPE */),
    _1(2 /* Keyboard.KEY_1 */, 49 /* GLFW.GLFW_KEY_1 */),
    _2(3 /* Keyboard.KEY_2 */, 50 /* GLFW.GLFW_KEY_2 */),
    _3(4 /* Keyboard.KEY_3 */, 51 /* GLFW.GLFW_KEY_3 */),
    _4(5 /* Keyboard.KEY_4 */, 52 /* GLFW.GLFW_KEY_4 */),
    _5(6 /* Keyboard.KEY_5 */, 53 /* GLFW.GLFW_KEY_5 */),
    _6(7 /* Keyboard.KEY_6 */, 54 /* GLFW.GLFW_KEY_6 */),
    _7(8 /* Keyboard.KEY_7 */, 55 /* GLFW.GLFW_KEY_7 */),
    _8(9 /* Keyboard.KEY_8 */, 56 /* GLFW.GLFW_KEY_8 */),
    _9(10 /* Keyboard.KEY_9 */, 57 /* GLFW.GLFW_KEY_9 */),
    _0(11 /* Keyboard.KEY_0 */, 48 /* GLFW.GLFW_KEY_0 */),
    MINUS(12 /* Keyboard.KEY_MINUS */, 45 /* GLFW.GLFW_KEY_MINUS */),
    EQUALS(13 /* Keyboard.KEY_EQUALS */, 61 /* GLFW.GLFW_KEY_EQUAL */),
    BACK(14 /* Keyboard.KEY_BACK */, 259 /* GLFW.GLFW_KEY_BACKSPACE */),
    TAB(15 /* Keyboard.KEY_TAB */, 258 /* GLFW.GLFW_KEY_TAB */),
    Q(16 /* Keyboard.KEY_Q */, 81 /* GLFW.GLFW_KEY_Q */),
    W(17 /* Keyboard.KEY_W */, 87 /* GLFW.GLFW_KEY_W */),
    E(18 /* Keyboard.KEY_E */, 69 /* GLFW.GLFW_KEY_E */),
    R(19 /* Keyboard.KEY_R */, 82 /* GLFW.GLFW_KEY_R */),
    T(20 /* Keyboard.KEY_T */, 84 /* GLFW.GLFW_KEY_T */),
    Y(21 /* Keyboard.KEY_Y */, 89 /* GLFW.GLFW_KEY_Y */),
    U(22 /* Keyboard.KEY_U */, 85 /* GLFW.GLFW_KEY_U */),
    I(23 /* Keyboard.KEY_I */, 73 /* GLFW.GLFW_KEY_I */),
    O(24 /* Keyboard.KEY_O */, 79 /* GLFW.GLFW_KEY_O */),
    P(25 /* Keyboard.KEY_P */, 80 /* GLFW.GLFW_KEY_P */),
    LBRACKET(26 /* Keyboard.KEY_LBRACKET */, 91 /* GLFW.GLFW_KEY_LEFT_BRACKET */),
    RBRACKET(27 /* Keyboard.KEY_RBRACKET */, 93 /* GLFW.GLFW_KEY_RIGHT_BRACKET */),
    RETURN(28 /* Keyboard.KEY_RETURN */, 257 /* GLFW.GLFW_KEY_ENTER */),
    LCONTROL(29 /* Keyboard.KEY_LCONTROL */, 341 /* GLFW.GLFW_KEY_LEFT_CONTROL */),
    A(30 /* Keyboard.KEY_A */, 65 /* GLFW.GLFW_KEY_A */),
    S(31 /* Keyboard.KEY_S */, 83 /* GLFW.GLFW_KEY_S */),
    D(32 /* Keyboard.KEY_D */, 68 /* GLFW.GLFW_KEY_D */),
    F(33 /* Keyboard.KEY_F */, 70 /* GLFW.GLFW_KEY_F */),
    G(34 /* Keyboard.KEY_G */, 71 /* GLFW.GLFW_KEY_G */),
    H(35 /* Keyboard.KEY_H */, 72 /* GLFW.GLFW_KEY_H */),
    J(36 /* Keyboard.KEY_J */, 74 /* GLFW.GLFW_KEY_J */),
    K(37 /* Keyboard.KEY_K */, 75 /* GLFW.GLFW_KEY_K */),
    L(38 /* Keyboard.KEY_L */, 76 /* GLFW.GLFW_KEY_L */),
    SEMICOLON(39 /* Keyboard.KEY_SEMICOLON */, 59 /* GLFW.GLFW_KEY_SEMICOLON */),
    APOSTROPHE(40 /* Keyboard.KEY_APOSTROPHE */, 39 /* GLFW.GLFW_KEY_APOSTROPHE */),
    GRAVE(41 /* Keyboard.KEY_GRAVE */, 96 /* GLFW.GLFW_KEY_GRAVE_ACCENT */),
    LSHIFT(42 /* Keyboard.KEY_LSHIFT */, 340 /* GLFW.GLFW_KEY_LEFT_SHIFT */),
    BACKSLASH(43 /* Keyboard.KEY_BACKSLASH */, 92 /* GLFW.GLFW_KEY_BACKSLASH */),
    Z(44 /* Keyboard.KEY_Z */, 90 /* GLFW.GLFW_KEY_Z */),
    X(45 /* Keyboard.KEY_X */, 88 /* GLFW.GLFW_KEY_X */),
    C(46 /* Keyboard.KEY_C */, 67 /* GLFW.GLFW_KEY_C */),
    V(47 /* Keyboard.KEY_V */, 86 /* GLFW.GLFW_KEY_V */),
    B(48 /* Keyboard.KEY_B */, 66 /* GLFW.GLFW_KEY_B */),
    N(49 /* Keyboard.KEY_N */, 78 /* GLFW.GLFW_KEY_N */),
    M(50 /* Keyboard.KEY_M */, 77 /* GLFW.GLFW_KEY_M */),
    COMMA(51 /* Keyboard.KEY_COMMA */, 44 /* GLFW.GLFW_KEY_COMMA */),
    PERIOD(52 /* Keyboard.KEY_PERIOD */, 46 /* GLFW.GLFW_KEY_PERIOD */),
    SLASH(53 /* Keyboard.KEY_SLASH */, 47 /* GLFW.GLFW_KEY_SLASH */),
    RSHIFT(54 /* Keyboard.KEY_RSHIFT */, 344 /* GLFW.GLFW_KEY_RIGHT_SHIFT */),
    MULTIPLY(55 /* Keyboard.KEY_MULTIPLY */, 332 /* GLFW.GLFW_KEY_KP_MULTIPLY */),
    LMENU(56 /* Keyboard.KEY_LMENU */, 342 /* GLFW.GLFW_KEY_LEFT_ALT */),
    SPACE(57 /* Keyboard.KEY_SPACE */, 32 /* GLFW.GLFW_KEY_SPACE */),
    CAPITAL(58 /* Keyboard.KEY_CAPITAL */, 280 /* GLFW.GLFW_KEY_CAPS_LOCK */),
    F1(59 /* Keyboard.KEY_F1 */, 290 /* GLFW.GLFW_KEY_F1 */),
    F2(60 /* Keyboard.KEY_F2 */, 291 /* GLFW.GLFW_KEY_F2 */),
    F3(61 /* Keyboard.KEY_F3 */, 292 /* GLFW.GLFW_KEY_F3 */),
    F4(62 /* Keyboard.KEY_F4 */, 293 /* GLFW.GLFW_KEY_F4 */),
    F5(63 /* Keyboard.KEY_F5 */, 294 /* GLFW.GLFW_KEY_F5 */),
    F6(64 /* Keyboard.KEY_F6 */, 295 /* GLFW.GLFW_KEY_F6 */),
    F7(65 /* Keyboard.KEY_F7 */, 296 /* GLFW.GLFW_KEY_F7 */),
    F8(66 /* Keyboard.KEY_F8 */, 297 /* GLFW.GLFW_KEY_F8 */),
    F9(67 /* Keyboard.KEY_F9 */, 298 /* GLFW.GLFW_KEY_F9 */),
    F10(68 /* Keyboard.KEY_F10 */, 299 /* GLFW.GLFW_KEY_F10 */),
    NUMLOCK(69 /* Keyboard.KEY_NUMLOCK */, 282 /* GLFW.GLFW_KEY_NUM_LOCK */),
    SCROLL(70 /* Keyboard.KEY_SCROLL */, 281 /* GLFW.GLFW_KEY_SCROLL_LOCK */),
    NUMPAD7(71 /* Keyboard.KEY_NUMPAD7 */, 327 /* GLFW.GLFW_KEY_KP_7 */),
    NUMPAD8(72 /* Keyboard.KEY_NUMPAD8 */, 328 /* GLFW.GLFW_KEY_KP_8 */),
    NUMPAD9(73 /* Keyboard.KEY_NUMPAD9 */, 329 /* GLFW.GLFW_KEY_KP_9 */),
    SUBTRACT(74 /* Keyboard.KEY_SUBTRACT */, 333 /* GLFW.GLFW_KEY_KP_SUBTRACT */),
    NUMPAD4(75 /* Keyboard.KEY_NUMPAD4 */, 324 /* GLFW.GLFW_KEY_KP_4 */),
    NUMPAD5(76 /* Keyboard.KEY_NUMPAD5 */, 325 /* GLFW.GLFW_KEY_KP_5 */),
    NUMPAD6(77 /* Keyboard.KEY_NUMPAD6 */, 326 /* GLFW.GLFW_KEY_KP_6 */),
    ADD(78 /* Keyboard.KEY_ADD */, 334 /* GLFW.GLFW_KEY_KP_ADD */),
    NUMPAD1(79 /* Keyboard.KEY_NUMPAD1 */, 321 /* GLFW.GLFW_KEY_KP_1 */),
    NUMPAD2(80 /* Keyboard.KEY_NUMPAD2 */, 322 /* GLFW.GLFW_KEY_KP_2 */),
    NUMPAD3(81 /* Keyboard.KEY_NUMPAD3 */, 323 /* GLFW.GLFW_KEY_KP_3 */),
    NUMPAD0(82 /* Keyboard.KEY_NUMPAD0 */, 320 /* GLFW.GLFW_KEY_KP_0 */),
    DECIMAL(83 /* Keyboard.KEY_DECIMAL */, 330 /* GLFW.GLFW_KEY_KP_DECIMAL */),
    F11(87 /* Keyboard.KEY_F11 */, 300 /* GLFW.GLFW_KEY_F11 */),
    F12(88 /* Keyboard.KEY_F12 */, 301 /* GLFW.GLFW_KEY_F12 */),
    F13(100 /* Keyboard.KEY_F13 */, 302 /* GLFW.GLFW_KEY_F13 */),
    F14(101 /* Keyboard.KEY_F14 */, 303 /* GLFW.GLFW_KEY_F14 */),
    F15(102 /* Keyboard.KEY_F15 */, 304 /* GLFW.GLFW_KEY_F15 */),
    NUMPADEQUALS(141 /* Keyboard.KEY_NUMPADEQUALS */, 336 /* GLFW.GLFW_KEY_KP_EQUAL */),
    NUMPADENTER(156 /* Keyboard.KEY_NUMPADENTER */, 335 /* GLFW.GLFW_KEY_KP_ENTER */),
    RCONTROL(157 /* Keyboard.KEY_RCONTROL */, 345 /* GLFW.GLFW_KEY_RIGHT_CONTROL */),
    DIVIDE(181 /* Keyboard.KEY_DIVIDE */, 331 /* GLFW.GLFW_KEY_KP_DIVIDE */),
    SYSRQ(183 /* Keyboard.KEY_SYSRQ */, 283 /* GLFW.GLFW_KEY_PRINT_SCREEN */),
    RMENU(184 /* Keyboard.KEY_RMENU */, 346 /* GLFW.GLFW_KEY_RIGHT_ALT */),
    PAUSE(197 /* Keyboard.KEY_PAUSE */, 284 /* GLFW.GLFW_KEY_PAUSE */),
    HOME(199 /* Keyboard.KEY_HOME */, 268 /* GLFW.GLFW_KEY_HOME */),
    UP(200 /* Keyboard.KEY_UP */, 265 /* GLFW.GLFW_KEY_UP */),
    PRIOR(201 /* Keyboard.KEY_PRIOR */, 266 /* GLFW.GLFW_KEY_PAGE_UP */),
    LEFT(203 /* Keyboard.KEY_LEFT */, 263 /* GLFW.GLFW_KEY_LEFT */),
    RIGHT(205 /* Keyboard.KEY_RIGHT */, 262 /* GLFW.GLFW_KEY_RIGHT */),
    END(207 /* Keyboard.KEY_END */, 269 /* GLFW.GLFW_KEY_END */),
    DOWN(208 /* Keyboard.KEY_DOWN */, 264 /* GLFW.GLFW_KEY_DOWN */),
    NEXT(209 /* Keyboard.KEY_NEXT */, 267 /* GLFW.GLFW_KEY_PAGE_DOWN */),
    INSERT(210 /* Keyboard.KEY_INSERT */, 260 /* GLFW.GLFW_KEY_INSERT */),
    DELETE(211 /* Keyboard.KEY_DELETE */, 261 /* GLFW.GLFW_KEY_DELETE */),
    LMETA(219 /* Keyboard.KEY_LMETA */, 343 /* GLFW.GLFW_KEY_LEFT_SUPER */),
    /** @deprecated */
    LWIN(219 /* Keyboard.KEY_LWIN */, 343 /* GLFW.GLFW_KEY_LEFT_SUPER */),
    RMETA(220 /* Keyboard.KEY_RMETA */, 347 /* GLFW.GLFW_KEY_RIGHT_SUPER */),
    /** @deprecated */
    RWIN(220 /* Keyboard.KEY_RWIN */, 347 /* GLFW.GLFW_KEY_RIGHT_SUPER */);

    private final int legacyId;
    private final int glfwId;

    public static KeyObject getKey(int id, boolean glfw) {
        for (KeyObject object : KeyObject.values()) {
            if (glfw) {
                if (object.glfwId == id) {
                    return object;
                }
            } else {
                if (object.legacyId == id) {
                    return object;
                }
            }
        }

        CheatBreaker.LOGGER.warn("Couldn't find key ({}) ID: {}", glfw ? "GLFW" : "Legacy", id);
        return null;
    }

    public static KeyObject getKey(int id) {
        return getKey(id, Ref.getMinecraftVersion().isNewerThan(MinecraftVersion.v1_12_2));
    }
}
