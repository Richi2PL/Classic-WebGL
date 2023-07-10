package net.PeytonPlayz585.lwjgl;

import static net.PeytonPlayz585.teavm.WebGL2RenderingContext.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;
import org.teavm.jso.typedarrays.ArrayBuffer;
import org.teavm.jso.typedarrays.Float32Array;
import org.teavm.jso.typedarrays.Int32Array;
import org.teavm.jso.typedarrays.Uint8Array;
import org.teavm.jso.webgl.WebGLBuffer;
import org.teavm.jso.webgl.WebGLFramebuffer;
import org.teavm.jso.webgl.WebGLProgram;
import org.teavm.jso.webgl.WebGLRenderbuffer;
import org.teavm.jso.webgl.WebGLShader;
import org.teavm.jso.webgl.WebGLTexture;
import org.teavm.jso.webgl.WebGLUniformLocation;

import net.PeytonPlayz585.main.MinecraftMain;
import net.PeytonPlayz585.minecraft.GlStateManager;
import net.PeytonPlayz585.minecraft.MinecraftClient;
import net.PeytonPlayz585.teavm.WebGL2RenderingContext;
import net.PeytonPlayz585.teavm.WebGLQuery;
import net.PeytonPlayz585.teavm.WebGLVertexArray;

public class LWJGL {
static WebGL2RenderingContext webgl = null;
	
	public static final void initWebGL(WebGL2RenderingContext wgl2rc) {
		webgl = wgl2rc;
	}
	
	public static final String _wgetShaderHeader() {
		return "#version 300 es";
	}
	
	public static final class TextureGL { 
		protected final WebGLTexture obj;
		public int w = -1;
		public int h = -1;
		public boolean nearest = true;
		public boolean anisotropic = false;
		protected TextureGL(WebGLTexture obj) { 
			this.obj = obj; 
		} 
	} 
	public static final class BufferGL { 
		protected final WebGLBuffer obj; 
		protected BufferGL(WebGLBuffer obj) { 
			this.obj = obj; 
		} 
	} 
	public static final class ShaderGL { 
		protected final WebGLShader obj; 
		protected ShaderGL(WebGLShader obj) { 
			this.obj = obj; 
		} 
	}
	private static int progId = 0;
	public static final class ProgramGL { 
		protected final WebGLProgram obj; 
		protected final int hashcode; 
		protected ProgramGL(WebGLProgram obj) { 
			this.obj = obj; 
			this.hashcode = ++progId;
		} 
	} 
	public static final class UniformGL { 
		protected final WebGLUniformLocation obj; 
		protected UniformGL(WebGLUniformLocation obj) { 
			this.obj = obj; 
		} 
	} 
	public static final class BufferArrayGL { 
		protected final WebGLVertexArray obj; 
		public boolean isQuadBufferBound; 
		protected BufferArrayGL(WebGLVertexArray obj) { 
			this.obj = obj; 
			this.isQuadBufferBound = false; 
		} 
	} 
	public static final class FramebufferGL { 
		protected final WebGLFramebuffer obj; 
		protected FramebufferGL(WebGLFramebuffer obj) { 
			this.obj = obj; 
		} 
	} 
	public static final class RenderbufferGL { 
		protected final WebGLRenderbuffer obj; 
		protected RenderbufferGL(WebGLRenderbuffer obj) { 
			this.obj = obj; 
		} 
	} 
	public static final class QueryGL { 
		protected final WebGLQuery obj; 
		protected QueryGL(WebGLQuery obj) { 
			this.obj = obj; 
		} 
	}
	
	public static final void _wglEnable(int p1) {
		webgl.enable(p1);
	}
	public static final void _wglClearDepth(float p1) {
		webgl.clearDepth(p1);
	}
	public static final void _wglDepthFunc(int p1) {
		webgl.depthFunc(p1);
	}
	public static final void _wglCullFace(int p1) {
		webgl.cullFace(p1);
	}
	private static int[] viewportCache = new int[4];
	public static final void _wglViewport(int p1, int p2, int p3, int p4) {
		viewportCache[0] = p1; viewportCache[1] = p2;
		viewportCache[2] = p3; viewportCache[3] = p4;
		webgl.viewport(p1, p2, p3, p4);
	}
	public static final void _wglClear(int p1) {
		webgl.clear(p1);
	}
	public static final void _wglClearColor(float p1, float p2, float p3, float p4) {
		webgl.clearColor(p1, p2, p3, p4);
	}
	public static final void _wglDisable(int p1) {
		webgl.disable(p1);
	}
	public static final int _wglGetError() {
		return webgl.getError();
	}
	public static final void _wglFlush() {
		//webgl.flush();
	}
	private static Uint8Array uploadBuffer = Uint8Array.create(ArrayBuffer.create(4 * 1024 * 1024));
	public static final void _wglTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, ByteBuffer p9) {
		if(p9 == null) {
			webgl.texImage2D(p1, p2, p3, p4, p5, p6, p7, p8, null);
		}else {
			int len = p9.remaining();
			Uint8Array uploadBuffer1 = uploadBuffer;
			for(int i = 0; i < len; ++i) {
				uploadBuffer1.set(i, (short) ((int)p9.get() & 0xff));
			}
			Uint8Array data = Uint8Array.create(uploadBuffer.getBuffer(), 0, len);
			webgl.texImage2D(p1, p2, p3, p4, p5, p6, p7, p8, data);
		}
	}
	public static final void _wglBlendFunc(int p1, int p2) {
		webgl.blendFunc(p1, p2);
	}
	public static final void _wglDepthMask(boolean p1) {
		webgl.depthMask(p1);
	}
	public static final void _wglColorMask(boolean p1, boolean p2, boolean p3, boolean p4) {
		webgl.colorMask(p1, p2, p3, p4);
	}
	public static final void _wglBindTexture(int p1, TextureGL p2) {
		webgl.bindTexture(p1, p2 == null ? null : p2.obj);
	}
	public static final void _wglCopyTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8) {
		webgl.copyTexSubImage2D(p1, p2, p3, p4, p5, p6, p7, p8);
	}
	public static final void _wglTexParameteri(int p1, int p2, int p3) {
		webgl.texParameteri(p1, p2, p3);
	}
	public static final void _wglTexParameterf(int p1, int p2, float p3) {
		webgl.texParameterf(p1, p2, p3);
	}
	public static final void _wglTexImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, IntBuffer p9) {
		int len = p9.remaining();
		Int32Array deevis = Int32Array.create(uploadBuffer.getBuffer());
		for(int i = 0; i < len; ++i) {
			deevis.set(i, p9.get());
		}
		Uint8Array data = Uint8Array.create(uploadBuffer.getBuffer(), 0, len*4);
		webgl.texImage2D(p1, p2, p3, p4, p5, p6, p7, p8, data);
	}
	public static final void _wglTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, IntBuffer p9) {
		int len = p9.remaining();
		Int32Array deevis = Int32Array.create(uploadBuffer.getBuffer());
		for(int i = 0; i < len; ++i) {
			deevis.set(i, p9.get());
		}
		Uint8Array data = Uint8Array.create(uploadBuffer.getBuffer(), 0, len*4);
		webgl.texSubImage2D(p1, p2, p3, p4, p5, p6, p7, p8, data);
	}
	public static final void _wglDeleteTextures(TextureGL p1) {
		webgl.deleteTexture(p1.obj);
	}
	public static final void _wglDrawArrays(int p1, int p2, int p3) {
		webgl.drawArrays(p1, p2, p3);
	}
	public static final void _wglDrawElements(int p1, int p2, int p3, int p4) {
		webgl.drawElements(p1, p2, p3, p4);
	}
	public static final TextureGL _wglGenTextures() {
		return new TextureGL(webgl.createTexture());
	}
	public static final void _wglTexSubImage2D(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, ByteBuffer p9) {
		int len = p9.remaining();
		for(int i = 0; i < len; ++i) {
			uploadBuffer.set(i, (short) ((int)p9.get() & 0xff));
		}
		Uint8Array data = Uint8Array.create(uploadBuffer.getBuffer(), 0, len);
		webgl.texSubImage2D(p1, p2, p3, p4, p5, p6, p7, p8, data);
	}
	public static final void _wglActiveTexture(int p1) {
		webgl.activeTexture(p1);
	}
	public static final ProgramGL _wglCreateProgram() {
		return new ProgramGL(webgl.createProgram());
	}
	public static final ShaderGL _wglCreateShader(int p1) {
		return new ShaderGL(webgl.createShader(p1));
	}
	public static final void _wglAttachShader(ProgramGL p1, ShaderGL p2) {
		webgl.attachShader(p1.obj, p2.obj);
	}
	public static final void _wglDetachShader(ProgramGL p1, ShaderGL p2) {
		webgl.detachShader(p1.obj, p2.obj);
	}
	public static final void _wglCompileShader(ShaderGL p1) {
		webgl.compileShader(p1.obj);
	}
	public static final void _wglLinkProgram(ProgramGL p1) {
		webgl.linkProgram(p1.obj);
	}
	public static final void _wglShaderSource(ShaderGL p1, String p2) {
		webgl.shaderSource(p1.obj, p2);
	}
	public static final String _wglGetShaderInfoLog(ShaderGL p1) {
		return webgl.getShaderInfoLog(p1.obj);
	}
	public static final String _wglGetProgramInfoLog(ProgramGL p1) {
		return webgl.getProgramInfoLog(p1.obj);
	}
	public static final boolean _wglGetShaderCompiled(ShaderGL p1) {
		return webgl.getShaderParameteri(p1.obj, COMPILE_STATUS) == 1;
	}
	public static final boolean _wglGetProgramLinked(ProgramGL p1) {
		return webgl.getProgramParameteri(p1.obj, LINK_STATUS) == 1;
	}
	public static final void _wglDeleteShader(ShaderGL p1) {
		webgl.deleteShader(p1.obj);
	}
	public static final void _wglDeleteProgram(ProgramGL p1) {
		webgl.deleteProgram(p1.obj);
	}
	public static final BufferGL _wglCreateBuffer() {
		return new BufferGL(webgl.createBuffer());
	}
	public static final void _wglDeleteBuffer(BufferGL p1) {
		webgl.deleteBuffer(p1.obj);
	}
	public static final void _wglBindBuffer(int p1, BufferGL p2) {
		webgl.bindBuffer(p1, p2 == null ? null : p2.obj);
	}
	public static final void _wglBufferData0(int p1, IntBuffer p2, int p3) {
		int len = p2.remaining();
		Int32Array deevis = Int32Array.create(uploadBuffer.getBuffer());
		for(int i = 0; i < len; ++i) {
			deevis.set(i, p2.get());
		}
		Uint8Array data = Uint8Array.create(uploadBuffer.getBuffer(), 0, len*4);
		webgl.bufferData(p1, data, p3);
	}
	public static final void _wglBufferSubData0(int p1, int p2, IntBuffer p3) {
		int len = p3.remaining();
		Int32Array deevis = Int32Array.create(uploadBuffer.getBuffer());
		for(int i = 0; i < len; ++i) {
			deevis.set(i, p3.get());
		}
		Uint8Array data = Uint8Array.create(uploadBuffer.getBuffer(), 0, len*4);
		webgl.bufferSubData(p1, p2, data);
	}
	public static final void _wglBufferData(int p1, Object p2, int p3) {
		webgl.bufferData(p1, (Int32Array)p2, p3);
	}
	public static final void _wglBufferSubData(int p1, int p2, Object p3) {
		webgl.bufferSubData(p1, p2, (Int32Array)p3);
	}
	public static final void _wglBindAttribLocation(ProgramGL p1, int p2, String p3) {
		webgl.bindAttribLocation(p1.obj, p2, p3);
	}
	public static final void _wglEnableVertexAttribArray(int p1) {
		webgl.enableVertexAttribArray(p1);
	}
	public static final void _wglDisableVertexAttribArray(int p1) {
		webgl.disableVertexAttribArray(p1);
	}
	public static final UniformGL _wglGetUniformLocation(ProgramGL p1, String p2) {
		WebGLUniformLocation u = webgl.getUniformLocation(p1.obj, p2);
		return u == null ? null : new UniformGL(u);
	}
	public static final void _wglBindAttributeLocation(ProgramGL p1, int p2, String p3) {
		webgl.bindAttribLocation(p1.obj, p2, p3);
	}
	public static final void _wglUniform1f(UniformGL p1, float p2) {
		if(p1 != null) webgl.uniform1f(p1.obj, p2);
	}
	public static final void _wglUniform2f(UniformGL p1, float p2, float p3) {
		if(p1 != null) webgl.uniform2f(p1.obj, p2, p3);
	}
	public static final void _wglUniform3f(UniformGL p1, float p2, float p3, float p4) {
		if(p1 != null) webgl.uniform3f(p1.obj, p2, p3, p4);
	}
	public static final void _wglUniform4f(UniformGL p1, float p2, float p3, float p4, float p5) {
		if(p1 != null) webgl.uniform4f(p1.obj, p2, p3, p4, p5);
	}
	public static final void _wglUniform1i(UniformGL p1, int p2) {
		if(p1 != null) webgl.uniform1i(p1.obj, p2);
	}
	public static final void _wglUniform2i(UniformGL p1, int p2, int p3) {
		if(p1 != null) webgl.uniform2i(p1.obj, p2, p3);
	}
	public static final void _wglUniform3i(UniformGL p1, int p2, int p3, int p4) {
		if(p1 != null) webgl.uniform3i(p1.obj, p2, p3, p4);
	}
	public static final void _wglUniform4i(UniformGL p1, int p2, int p3, int p4, int p5) {
		if(p1 != null) webgl.uniform4i(p1.obj, p2, p3, p4, p5);
	}
	private static Float32Array mat2 = Float32Array.create(4);
	private static Float32Array mat3 = Float32Array.create(9);
	private static Float32Array mat4 = Float32Array.create(16);
	public static final void _wglUniformMat2fv(UniformGL p1, float[] mat) {
		mat2.set(mat);
		if(p1 != null) webgl.uniformMatrix2fv(p1.obj, false, mat2);
	}
	public static final void _wglUniformMat3fv(UniformGL p1, float[] mat) {
		mat3.set(mat);
		if(p1 != null) webgl.uniformMatrix3fv(p1.obj, false, mat3);
	}
	public static final void _wglUniformMat4fv(UniformGL p1, float[] mat) {
		mat4.set(mat);
		if(p1 != null) webgl.uniformMatrix4fv(p1.obj, false, mat4);
	}
	private static int currentProgram = -1;
	public static final void _wglUseProgram(ProgramGL p1) {
		if(p1 != null && currentProgram != p1.hashcode) {
			currentProgram = p1.hashcode;
			webgl.useProgram(p1.obj);
		}
	}
	public static final void _wglGetParameter(int p1, int size, int[] ret) {
		if(p1 == _wGL_VIEWPORT) {
			ret[0] = viewportCache[0];
			ret[1] = viewportCache[1];
			ret[2] = viewportCache[2];
			ret[3] = viewportCache[3];
		}
	}
	public static final void _wglPolygonOffset(float p1, float p2) {
		webgl.polygonOffset(p1, p2);
	}
	public static final void _wglVertexAttribPointer(int p1, int p2, int p3, boolean p4, int p5, int p6) {
		webgl.vertexAttribPointer(p1, p2, p3, p4, p5, p6);
	}
	public static final void _wglBindFramebuffer(int p1, FramebufferGL p2) {
		webgl.bindFramebuffer(p1, p2 == null ? null : p2.obj);
	}
	public static final FramebufferGL _wglCreateFramebuffer() {
		return new FramebufferGL(webgl.createFramebuffer());
	}
	public static final void _wglDeleteFramebuffer(FramebufferGL p1) {
		webgl.deleteFramebuffer(p1.obj);
	}
	public static final void _wglFramebufferTexture2D(int p1, TextureGL p2) {
		webgl.framebufferTexture2D(FRAMEBUFFER, p1, TEXTURE_2D, p2 == null ? null : p2.obj, 0);
	}
	public static final QueryGL _wglCreateQuery() { 
		return new QueryGL(webgl.createQuery()); 
	}
	public static final void _wglBeginQuery(int p1, QueryGL p2) { 
		webgl.beginQuery(p1, p2.obj); 
	}
	public static final void _wglEndQuery(int p1) { 
		webgl.endQuery(p1); 
	}
	public static final void _wglDeleteQuery(QueryGL p1) { 
		webgl.deleteQuery(p1.obj);
	}
	public static final int _wglGetQueryObjecti(QueryGL p1, int p2) { 
		return webgl.getQueryParameter(p1.obj, p2);
	}
	public static final BufferArrayGL _wglCreateVertexArray() {
		return new BufferArrayGL(webgl.createVertexArray());
	}
	public static final void _wglDeleteVertexArray(BufferArrayGL p1) {
		webgl.deleteVertexArray(p1.obj);
	}
	public static final void _wglBindVertexArray(BufferArrayGL p1) {
		webgl.bindVertexArray(p1 == null ? null : p1.obj);
	}
	public static final void _wglDrawBuffer(int p1) {
		webgl.drawBuffers(new int[] { p1 });
	}
	public static final RenderbufferGL _wglCreateRenderBuffer() {
		return new RenderbufferGL(webgl.createRenderbuffer());
	}
	public static final void _wglBindRenderbuffer(RenderbufferGL p1) {
		webgl.bindRenderbuffer(RENDERBUFFER, p1 == null ? null : p1.obj);
	}
	public static final void _wglRenderbufferStorage(int p1, int p2, int p3) {
		webgl.renderbufferStorage(RENDERBUFFER, p1, p2, p3);
	}
	public static final void _wglFramebufferRenderbuffer(int p1, RenderbufferGL p2) {
		webgl.framebufferRenderbuffer(FRAMEBUFFER, p1, RENDERBUFFER, p2 == null ? null : p2.obj);
	}
	public static final void _wglDeleteRenderbuffer(RenderbufferGL p1) {
		webgl.deleteRenderbuffer(p1.obj);
	}
	public static final void _wglRenderbufferStorageMultisample(int p1, int p2, int p3, int p4) {
		webgl.renderbufferStorageMultisample(RENDERBUFFER, p1, p2, p3, p4);
	}
	public static final void _wglBlitFramebuffer(int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9, int p10) {
		webgl.blitFramebuffer(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
	}
	public static final int _wglGetAttribLocation(ProgramGL p1, String p2) {
		return webgl.getAttribLocation(p1.obj, p2);
	}
	
	@JSBody(params = { "ctx", "p" }, script = "return ctx.getTexParameter(0x0DE1, p) | 0;")
	private static final native int __wglGetTexParameteri(WebGL2RenderingContext ctx, int p);
	public static final int _wglGetTexParameteri(int p1) {
		return __wglGetTexParameteri(webgl, p1);
	}
	@JSBody(params = { "ctx", "p" }, script = "return (0.0 + ctx.getTexParameter(0x0DE1, p));")
	private static final native float __wglGetTexParameterf(WebGL2RenderingContext ctx, int p);
	public static final float _wglGetTexParameterf(int p1) {
		return __wglGetTexParameterf(webgl, p1);
	}
	
	public static final int _wArrayByteLength(Object obj) {
		return ((Int32Array)obj).getByteLength();
	}
	
	public static final Object _wCreateLowLevelIntBuffer(int len) {
		return Int32Array.create(len);
	}
	
	private static int appendbufferindex = 0;
	private static Int32Array appendbuffer = Int32Array.create(ArrayBuffer.create(525000*4));

	public static final void _wAppendLowLevelBuffer(Object arr) {
		Int32Array a = ((Int32Array)arr);
		if(appendbufferindex + a.getLength() < appendbuffer.getLength()) {
			appendbuffer.set(a, appendbufferindex);
			appendbufferindex += a.getLength();
		}
	}
	
	public static final Object _wGetLowLevelBuffersAppended() {
		Int32Array ret = Int32Array.create(appendbuffer.getBuffer(), 0, appendbufferindex);
		appendbufferindex = 0;
		return ret;
	}
	
	@JSBody(params = { "obj" }, script = "if(obj.commit) obj.commit();")
	private static native int commitContext(JSObject obj);
	
	public static final void updateDisplay() {
		commitContext(webgl);
		MinecraftMain.canvasContext.drawImage(MinecraftMain.canvasBack, 0d, 0d, MinecraftMain.canvas.getWidth(), MinecraftMain.canvas.getHeight());
		
		int ww = MinecraftMain.canvas.getClientWidth();
		int hh = MinecraftMain.canvas.getClientHeight();
		if(ww != MinecraftMain.width || hh != MinecraftMain.height) {
			MinecraftMain.width = ww;
			MinecraftMain.height = hh;
			MinecraftMain.canvasBack.setWidth(ww);
			MinecraftMain.canvasBack.setHeight(hh);
		}
		
		try {
			Thread.sleep(1l);
		} catch (InterruptedException e) {
			;
		}
	}
	
	public static final byte[] loadResourceBytes(String path) {
		return MinecraftClient.getResource(path);
	}
	
	public static final String fileContents(String path) {
		byte[] contents = loadResourceBytes(path);
		if(contents == null) {
			return null;
		}else {
			return new String(contents, Charset.forName("UTF-8"));
		}
	}
	
	public static final int _wGL_TEXTURE_2D = TEXTURE_2D;
	public static final int _wGL_DEPTH_TEST = DEPTH_TEST;
	public static final int _wGL_LEQUAL = LEQUAL;
	public static final int _wGL_GEQUAL = GEQUAL;
	public static final int _wGL_GREATER = GREATER;
	public static final int _wGL_LESS = LESS;
	public static final int _wGL_BACK = BACK;
	public static final int _wGL_FRONT = FRONT;
	public static final int _wGL_FRONT_AND_BACK = FRONT_AND_BACK;
	public static final int _wGL_COLOR_BUFFER_BIT = COLOR_BUFFER_BIT;
	public static final int _wGL_DEPTH_BUFFER_BIT = DEPTH_BUFFER_BIT;
	public static final int _wGL_BLEND = BLEND;
	public static final int _wGL_RGBA = RGBA;
	public static final int _wGL_RGB = RGB;
	public static final int _wGL_RGB8 = RGB8;
	public static final int _wGL_RGBA8 = RGBA8;
	public static final int _wGL_UNSIGNED_BYTE = UNSIGNED_BYTE;
	public static final int _wGL_UNSIGNED_SHORT = UNSIGNED_SHORT;
	public static final int _wGL_SRC_ALPHA = SRC_ALPHA;
	public static final int _wGL_ONE_MINUS_SRC_ALPHA = ONE_MINUS_SRC_ALPHA;
	public static final int _wGL_ONE_MINUS_DST_COLOR = ONE_MINUS_DST_COLOR;
	public static final int _wGL_ONE_MINUS_SRC_COLOR = ONE_MINUS_SRC_COLOR;
	public static final int _wGL_ZERO = ZERO;
	public static final int _wGL_CULL_FACE = CULL_FACE;
	public static final int _wGL_TEXTURE_MIN_FILTER = TEXTURE_MIN_FILTER;
	public static final int _wGL_TEXTURE_MAG_FILTER = TEXTURE_MAG_FILTER;
	public static final int _wGL_LINEAR = LINEAR;
	public static final int _wGL_EQUAL = EQUAL;
	public static final int _wGL_SRC_COLOR = SRC_COLOR;
	public static final int _wGL_ONE = ONE;
	public static final int _wGL_NEAREST = NEAREST;
	public static final int _wGL_CLAMP = CLAMP_TO_EDGE;
	public static final int _wGL_TEXTURE_WRAP_S = TEXTURE_WRAP_S;
	public static final int _wGL_TEXTURE_WRAP_T = TEXTURE_WRAP_T;
	public static final int _wGL_REPEAT = REPEAT;
	public static final int _wGL_DST_COLOR = DST_COLOR;
	public static final int _wGL_DST_ALPHA = DST_ALPHA;
	public static final int _wGL_FLOAT = FLOAT;
	public static final int _wGL_SHORT = SHORT;
	public static final int _wGL_TRIANGLES = TRIANGLES;
	public static final int _wGL_TRIANGLE_STRIP = TRIANGLE_STRIP;
	public static final int _wGL_TRIANGLE_FAN = TRIANGLE_FAN;
	public static final int _wGL_LINE_STRIP = LINE_STRIP;
	public static final int _wGL_LINES = LINES;
	public static final int _wGL_PACK_ALIGNMENT = PACK_ALIGNMENT;
	public static final int _wGL_UNPACK_ALIGNMENT = UNPACK_ALIGNMENT;
	public static final int _wGL_TEXTURE0 = TEXTURE0;
	public static final int _wGL_TEXTURE1 = TEXTURE1;
	public static final int _wGL_TEXTURE2 = TEXTURE2;
	public static final int _wGL_TEXTURE3 = TEXTURE3;
	public static final int _wGL_VIEWPORT = VIEWPORT;
	public static final int _wGL_VERTEX_SHADER = VERTEX_SHADER;
	public static final int _wGL_FRAGMENT_SHADER = FRAGMENT_SHADER;
	public static final int _wGL_ARRAY_BUFFER = ARRAY_BUFFER;
	public static final int _wGL_ELEMENT_ARRAY_BUFFER = ELEMENT_ARRAY_BUFFER;
	public static final int _wGL_STATIC_DRAW = STATIC_DRAW;
	public static final int _wGL_DYNAMIC_DRAW = DYNAMIC_DRAW;
	public static final int _wGL_INVALID_ENUM = INVALID_ENUM;
	public static final int _wGL_INVALID_VALUE= INVALID_VALUE;
	public static final int _wGL_INVALID_OPERATION = INVALID_OPERATION;
	public static final int _wGL_OUT_OF_MEMORY = OUT_OF_MEMORY;
	public static final int _wGL_CONTEXT_LOST_WEBGL = CONTEXT_LOST_WEBGL;
	public static final int _wGL_FRAMEBUFFER_COMPLETE = FRAMEBUFFER_COMPLETE;
	public static final int _wGL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = FRAMEBUFFER_INCOMPLETE_ATTACHMENT;
	public static final int _wGL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT;
	public static final int _wGL_COLOR_ATTACHMENT0 = COLOR_ATTACHMENT0;
	public static final int _wGL_DEPTH_STENCIL_ATTACHMENT = DEPTH_STENCIL_ATTACHMENT;
	public static final int _wGL_DEPTH_STENCIL = DEPTH_STENCIL;
	public static final int _wGL_NEAREST_MIPMAP_LINEAR = NEAREST_MIPMAP_LINEAR; 
	public static final int _wGL_LINEAR_MIPMAP_LINEAR = LINEAR_MIPMAP_LINEAR; 
	public static final int _wGL_LINEAR_MIPMAP_NEAREST = LINEAR_MIPMAP_NEAREST; 
	public static final int _wGL_NEAREST_MIPMAP_NEAREST = NEAREST_MIPMAP_NEAREST;
	public static final int _wGL_TEXTURE_MAX_LEVEL = TEXTURE_MAX_LEVEL; 
	public static final int _wGL_UNSIGNED_INT_24_8 = UNSIGNED_INT_24_8;
	public static final int _wGL_UNSIGNED_INT = UNSIGNED_INT;
	public static final int _wGL_ANY_SAMPLES_PASSED = ANY_SAMPLES_PASSED; 
	public static final int _wGL_QUERY_RESULT = QUERY_RESULT;
	public static final int _wGL_QUERY_RESULT_AVAILABLE = QUERY_RESULT_AVAILABLE;
	public static final int _wGL_TEXTURE_MAX_ANISOTROPY = TEXTURE_MAX_ANISOTROPY_EXT;
	public static final int _wGL_DEPTH24_STENCIL8 = DEPTH24_STENCIL8;
	public static final int _wGL_DEPTH_COMPONENT32F = DEPTH_COMPONENT32F;
	public static final int _wGL_DEPTH_ATTACHMENT = DEPTH_ATTACHMENT;
	public static final int _wGL_MULTISAMPLE = -1;
	public static final int _wGL_LINE_SMOOTH = -1;
	public static final int _wGL_READ_FRAMEBUFFER = READ_FRAMEBUFFER;
	public static final int _wGL_DRAW_FRAMEBUFFER = DRAW_FRAMEBUFFER;
	public static final int _wGL_FRAMEBUFFER = FRAMEBUFFER;
	public static final int _wGL_POLYGON_OFFSET_FILL = POLYGON_OFFSET_FILL;
}
