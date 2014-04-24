/*
 * mat.cpp
 *
 *  Created on: Apr 24, 2014
 *      Author: mc
 */
#include <opencv2/opencv.hpp>
#include <iostream>

using namespace cv;
using namespace std;

int main(int argc, char** argv) {

	Mat A;
	A = imread(argv[1], CV_LOAD_IMAGE_COLOR);
	//Mat B;

	//Mat B(A);
	/*  A'nın aynısından B adında oluşturur.
	 * değişiklik yapıldığında her ikisi etkilenir.*/

	//Mat B=A.clone();
	/*  A'nın içindeki değerleri B'e kopyalar.
	 * Herhangi biri değiştirildiğinde diğeri etkilenmez.*/

	//A.copyTo(B);// A'nın değerlerini B'nin içine kopyalar.
	/*  A'nın içindeki değerleri B'e kopyalar.
	 * Herhangi biri değiştirildiğinde diğeri etkilenmez.
	 * B=A.clone() ile aynı görevi görür.*/

	//B=A;
	/* B'yi A'ya eşitler. Yapılan değişiklikler ikisini de
	 * etkiler.*/

	// B-=A;
	/* '=' '-=' '+=' '*=' gibi operatörler tanımlanmıştır.
	 */

	//Mat B(A,Rect(150,62,500,500) );
	/* Bu definition ile B , A'nın belirtilen parçasına
	 *(dikdörtgen) eşitlenmiştir.   */

	//Mat B(A,Range::all(),Range(1,200));
	/* Bu definition ile A'nın belirli
	 * satır ve sütun aralığını B'e kopyalarız.*/

	//Mat B(2,2,CV_8UC3,Scalar(255,255,255));
	/* Bu definition ile B matrisi RGB değeri belirtilen
	 * sayısı verilen pixellerden oluşur.*/

	//Mat B(10,10,CV_8UC3,Scalar(0,0,255));

	IplImage* img = cvLoadImage("image32.jpeg", 1);
	Mat C(img);
	/*Bu kod parçası ile resim IplImage türünde kaydedildikten sonra
	 * nasıl mat türüne çevirileceğini görüyoruz.*/

	Mat D = Mat(4, 4, CV_8UC3);
	randu(D, Scalar::all(0), Scalar::all(255));
	/* randu fonksiyonu değerler arasındaki herhangi rasgele sayılarla
	 * matrisi doldurur.*/

	cout << D << endl;

	namedWindow("Image", WINDOW_NORMAL);
	//namedWindow("Image2", WINDOW_NORMAL);
	namedWindow("Image3", WINDOW_NORMAL);
	imshow("Image", A);
	//imshow("Image2", B);
	imshow("Image3", D);

	waitKey(0);

	return 0;
}

