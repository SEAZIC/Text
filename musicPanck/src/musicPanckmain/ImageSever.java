package musicPanckmain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class ImageSever {
	private String FS = File.separator;
	private static HashMap<String,BufferedImage> SeverImage;
	private String FileParentPath;
	public ImageSever() {
		// TODO Auto-generated constructor stub
		SeverImage =new HashMap<>();
		FileParentPath = "Image";
	}
	/** イメージのロード
	 * @param settingname ファイルを呼び出すときのキー値
	 * @param FileName ファイルの名前 */
	public void createImage(String settingname,String FileName){
		File file = new File(FileParentPath+FS+FileName);
		System.out.println(FileParentPath+FS+FileName);
		SeverImage.put(settingname, openImageFile(file));
	}
	/** イメージのロード
	 * @param 画像のファイル名 
	 * @return イメージファイル*/
	public BufferedImage openImageFile(File file) {

		BufferedImage i = null;
		Image img = new ImageIcon(file.getPath()).getImage();
		i = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);

		Graphics g = i.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();

		return i;
	}
	public void loadImage(){

	}
	public BufferedImage getImage(String S){
		return SeverImage.get(S);
	}
}
