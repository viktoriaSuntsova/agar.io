package engines.views;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author homepc
 */
public class GameView extends ApplicationAdapter {
    
    /**
     * Инициализация ресурсов игры
     */
    @Override
    public void create () {
        m_camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        m_camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        
        m_camera.update();
        m_current_camera = m_camera;
        
        m_batch = new SpriteBatch();
        m_ctx = new engines.views.Graphics2D(m_batch);
        
        this.initResources();
    }
    
    /**
     * Обновляет состояние игры
     * @param elapsedTime время, прошедшее с предыдущего обновления
     */
    public void update(long elapsedTime) {
        
    }
    
    /**
     * Рендеринг игры
     */
    @Override
    public void render () {
        m_camera.update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update((long) (Gdx.graphics.getDeltaTime() * 1000.0));
        
        m_ctx.getBatch().setProjectionMatrix(m_camera.combined);
        
        m_ctx.begin();
        renderInContext(m_ctx);
        m_ctx.end();
    }
    
    /**
     * Инициализация ресурсов игры
     */
    public void initResources() { 
        
    }
    
    /**
     * Отрисовывет состояние игры
     * @param g контекст
     */    
    public void renderInContext(engines.views.Graphics2D g) {
        
    }
    
    /**
     * Освобождение ресурсов игры
     */
    @Override
    public void dispose () {
        //TextureManager.disposeTextures();
    }
    
    /**
     * Получение координаты X курсора мыши в окне
     * @return координата X курсора в окне
     */
    public int getMouseX() {
        return Gdx.input.getX() - Gdx.graphics.getWidth() / 2;
    }

    /**
     * Получение координаты Y курсора мыши в окне
     * @return координата Y курсора в окне 
     */    
    public int getMouseY() {
        return (Gdx.graphics.getHeight() / 2 - Gdx.input.getY());
    }
    
    /**
     * Камера
     */
    OrthographicCamera m_camera;
    
    /**
     * Глобальная камера игры для того, чтобы фон мог получить текущее смещение 
     * камеры
     */
    static Camera m_current_camera;
    
    /**
     * Batch для рисования
     */
    SpriteBatch m_batch;
    
    /**
     * Псевдо-графический контекст. В данном случае - контейнер длв Batch
     */
    Graphics2D  m_ctx;
}
