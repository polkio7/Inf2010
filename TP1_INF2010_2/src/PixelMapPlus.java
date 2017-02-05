import java.awt.PageAttributes.ColorType;



/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// compl�ter
		
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width ; col++)
			{
				imageData[row][col] = imageData[row][col].Negative(); 
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width ; col++)
			{
				imageData[row][col] = imageData[row][col].toBWPixel(); 
			}
		}
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// compl�ter
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width ; col++)
			{
				imageData[row][col] = imageData[row][col].toGrayPixel(); 
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width ; col++)
			{
				imageData[row][col] = imageData[row][col].toColorPixel(); 
			}
		}
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width ; col++)
			{
				imageData[row][col] = imageData[row][col].toTransparentPixel(); 
			}
		}
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// compl�ter
		double rotMat [][]= new double[][] {
			{Math.cos(angleRadian), Math.sin(angleRadian), -Math.cos(angleRadian)*x -Math.sin(angleRadian)*y+x},
			{-Math.sin(angleRadian), Math.cos(angleRadian), Math.sin(angleRadian)*x -Math.cos(angleRadian)*y+y},
			{0,0,1}
		};
		PixelMap imageTemp = new PixelMap(this.imageType, height, width);
		
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){
				double[] pos = new double[2]; 
				pos[0] = (rotMat[0][0]*col + rotMat[0][1]*row + rotMat[0][2]);
				pos[1] = (rotMat[1][0]*col + rotMat[1][1]*row + rotMat[1][2]);
				if(pos[0] >= 0 && pos[0] < width && pos[1] >= 0 && pos[1] < height){
					imageTemp.imageData[row][col] = imageData[(int) pos[1]][(int) pos[0]];
				}
			}
		}
		imageData = imageTemp.imageData;
		
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		// compl�ter - fait
		
		//Creer une image blanche temporaire de la nouvelle taille
		PixelMap imageTemp = new PixelMap(this.imageType,h,w);
		
		double xRatio = w / (double)this.width;
		double yRatio = h / (double)this.height;
		
		for(int row=0; row < h; row++)
		{
			for(int col=0; col < w; col++)
			{
				imageTemp.imageData[row][col] = this.imageData[(int) (row/yRatio)][(int) (col/xRatio)];
			}
		}
		imageData = imageTemp.imageData;
		height = h;
		width = w;
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// compl�ter
		//Check si le inset ne dépasse pas l'image
		if(height < row0 || width < col0)
			throw new IllegalArgumentException();
		
		for(int row=0; row < pm.height; row++)
		{
			for(int col=0;col < pm.width; col++)
			{
				//inset negatif
				if( 0<=(row+row0) && 0<=(col+col0) && height>(row+row0) && width>(col+col0) )
					this.imageData[row+row0][col+col0] = pm.imageData[row][col];
			}
		}
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		
		// compl�ter	
		//Creer une image blanche temporaire de la nouvelle taille
		PixelMap imageTemp = new PixelMap(this.imageType,h,w);
		//si tu crop plus petit que l'image initiale
		if(h<height && w<width)
		//Copie les infos nécessaire
		for(int row=0; row<imageTemp.height; row++)
		{
			for(int col=0; col<imageTemp.width ;col++)
			{
				imageTemp.imageData[row][col] = this.imageData[row][col];
			}
		}
		
		imageData=imageTemp.imageData;
		height=h;
		width=w;
	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter		
		PixelMap imageTemp = new PixelMap(this.imageType,height,width);
		
		//copie à la position translate
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				//Condition pour translate + et translate -
				if(0<=row-rowOffset && row-rowOffset < height && 0<=col-colOffset && col-colOffset < width)
					imageTemp.imageData[row][col] = this.imageData[row-rowOffset][col-colOffset];
			}
		}
		//Fait pointer imageData sur la nouvelle imageData créé
		imageData = imageTemp.imageData;
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		// compl�ter - fait
		int semiWidth = (int) (width/(zoomFactor*2));
		int semiHeight = (int) (height/(zoomFactor*2));
		if(x - semiWidth <= 0)
			x += semiWidth - x;
		if(x + semiWidth >= width)
			x -= width + (semiWidth - x);
		if(y - semiHeight <= 0)
			y += semiHeight - y;
		if(y + semiHeight >= height)
			y -= height + (semiHeight - y);
		PixelMapPlus imageTemp = new PixelMapPlus(this.imageType,(int)(height/zoomFactor),(int)(width/zoomFactor));
		for(int row=0; row<height/zoomFactor; row++){
			for(int col=0; col<width/zoomFactor; col++){
				imageTemp.imageData[(int) (row)][(int) (col)] = this.imageData[(int) (row+y-height/(2*zoomFactor))][(int) (col+x-width/(2*zoomFactor))];
			}
		}
		imageTemp.resize(width, height);
		imageData = imageTemp.imageData;

	}

	/**
	 * Effectue un remplacement de tout les pixels dont la valeur est entre min et max 
	 * avec newPixel
	 * @param min : La valeur miniale d'un pixel
	 * @param max : La valeur maximale d'un pixel  
	 * @param newPixel : Le pixel qui remplacera l'ancienne couleur 
	 * (sa valeur est entre min et max)
	 */
	public void replaceColor(AbstractPixel min, AbstractPixel max,
			AbstractPixel newPixel) {
		// compl�ter
		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width ;col++)
			{
				//	plus grand que le min 					et 					plus petit que le max
				if( imageData[row][col].compareTo(min) == 1  && imageData[row][col].compareTo(max) == -1)
					imageData[row][col] = newPixel;
			}
		}
	}//fin méthode
}//fin classe
