unit DeleteProduct;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs,IDHTTP, Vcl.StdCtrls, Vcl.ExtCtrls;

type
  TDeleteProductForm = class(TForm)
    lblTitreDeleteProduct: TLabel;
    PanelBas: TPanel;
    btnDeleteProduct: TButton;
    PanelGauche: TPanel;
    Panel6: TPanel;
    lblchoixId: TLabel;
    cbIdProduct: TComboBox;
    procedure FormCreate(Sender: TObject);
    procedure btnDeleteProductClick(Sender: TObject);
  private
    { Déclarations privées }
  public
    { Déclarations publiques }
  end;

var
  DeleteProductForm: TDeleteProductForm;

implementation

{$R *.dfm}

procedure TDeleteProductForm.btnDeleteProductClick(Sender: TObject);
var
 lHTTP: TIdHTTP;
 chaine,res : string;
begin
  lHTTP := TIdHTTP.Create;
  try
    chaine := 'http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/deleteProduct/' + cbIdProduct.Text;
    res := lHTTP.Get(chaine);
    ShowMessage(res);
  finally
    lHTTP.Free;
  end;
end;

procedure TDeleteProductForm.FormCreate(Sender: TObject);
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
