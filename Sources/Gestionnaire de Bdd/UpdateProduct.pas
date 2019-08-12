unit UpdateProduct;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs,IDHTTP, Vcl.StdCtrls, Vcl.ExtCtrls;

type
  TUpdateProductForm = class(TForm)
    lblTitreUpdateProduct: TLabel;
    Panel1: TPanel;
    Panel4: TPanel;
    Label4: TLabel;
    EditType: TEdit;
    Panel5: TPanel;
    Label1: TLabel;
    Edittags: TEdit;
    Panel6: TPanel;
    Label3: TLabel;
    EditPrice: TEdit;
    Panel7: TPanel;
    Label5: TLabel;
    editProductName: TEdit;
    Panel8: TPanel;
    Label6: TLabel;
    editIdShop: TEdit;
    PanelBas: TPanel;
    btnUpdateProduct: TButton;
    Panel2: TPanel;
    lblchoixId: TLabel;
    cbIdProduct: TComboBox;
    procedure btnUpdateProductClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Déclarations privées }
  public
    { Déclarations publiques }
  end;

var
  UpdateProductForm: TUpdateProductForm;

implementation

{$R *.dfm}

procedure TUpdateProductForm.btnUpdateProductClick(Sender: TObject);
var
 lHTTP: TIdHTTP;
 chaine,res : string;
begin
  lHTTP := TIdHTTP.Create;
  try
    chaine := 'http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/updateProduct/' + cbIdProduct.Text +'/'+  editIdShop.Text +'/'+
     editproductName.Text + '/' + EditPrice.Text + '/' + EditTags.Text + '/' + EditType.Text;
    chaine :=  stringreplace(chaine, 'ô', 'o',[rfReplaceAll, rfIgnoreCase]);
    chaine :=  stringreplace(chaine, 'é', 'e',[rfReplaceAll, rfIgnoreCase]);
    res := lHTTP.Get(chaine);
    ShowMessage(res);
  finally
    lHTTP.Free;
  end;
end;

procedure TUpdateProductForm.FormCreate(Sender: TObject);
var
  lhttp : TIdHTTP;
  result : Tstringlist;
  i : Integer;
begin
  lHTTP := TIdHTTP.Create;
  result := TStringList.Create;
  try
  // lancer une fonction récupérant les identifiants des utilisateurs et les mettre dans la combobox
    result.CommaText := lHTTP.Get('http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/getProductsId');
    for i := 0 to result.Count-1 do
    begin
      result[i] := stringreplace(result[i], '[', '',[rfReplaceAll, rfIgnoreCase]);
      result[i] := stringreplace(result[i], ']', '',[rfReplaceAll, rfIgnoreCase]);
      cbIdProduct.Items.Add(result[i]);
    end;
  finally
    result.Free;
    lhttp.Free;
  end;
end;

end.
