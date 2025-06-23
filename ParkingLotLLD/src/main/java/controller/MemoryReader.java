package controller;

import org.teavm.interop.Import;
import org.teavm.jso.JSBody;

public final class MemoryReader {

    @Import(module = "env", name = "readString")
    @JSBody(params = { "ptr", "len" }, script =
            "return String.fromCharCode.apply(null, new Uint8Array(teavm.exports.memory.buffer, ptr, len));")
    public static native String readString(int ptr, int len);
}