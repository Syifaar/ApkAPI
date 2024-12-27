#include <iostream>
using namespace std;

int main(){
	int panjangArray,i,j;
	
	cout<<"Masukkan panjang array: ";
	cin>>panjangArray;
	int bilanganBulat[panjangArray];
	
	for(int i=0; i<panjangArray; i++){
		cout<<"Masukkan nilai ke index" <<i<< " = ";
		cin>>bilanganBulat[i];
	}
	
	cout<<"Tampilkan semua data"<<endl;
	for(int j=0; j<panjangArray; j++){
		cout<<bilanganBulat[j]<<" ";
	}
	
}