package others.trianglify.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;


import java.util.Collection;

import others.trianglify.domain.Triangle;
import others.trianglify.generator.color.BrewerColorGenerator;
import others.trianglify.generator.color.ColorGenerator;

/**
 * Triangle renderer
 *
 * @author manolovn
 */
public class TriangleRenderer {

    private Paint trianglePaint;
    private ColorGenerator colorGenerator;

    public TriangleRenderer() {
        initPaint();
    }

    public TriangleRenderer(ColorGenerator colorGenerator) {
        this.colorGenerator = colorGenerator;

        initPaint();
    }

    private void initPaint() {
        trianglePaint = new Paint();
        trianglePaint.setStyle(Paint.Style.FILL);
        trianglePaint.setAntiAlias(true);

        if (colorGenerator == null) {
            colorGenerator = new BrewerColorGenerator();
        }
    }

    public void render(Collection<Triangle> triangles, Canvas canvas) {
        colorGenerator.setCount(triangles.size());
        for (Triangle triangle : triangles) {
            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);

            path.moveTo(triangle.a.x, triangle.a.y);
            path.lineTo(triangle.b.x, triangle.b.y);
            path.lineTo(triangle.c.x, triangle.c.y);
            path.lineTo(triangle.a.x, triangle.a.y);

            path.close();

            trianglePaint.setColor(colorGenerator.nextColor());
            canvas.drawPath(path, trianglePaint);
        }
    }
}
