unit DeleteUser;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs,IdHTTP, Vcl.StdCtrls, Vcl.ExtCtrls;

type
  TDeleteUserForm = class(TForm)
    lblTitreDeleteUser: TLabel;
    PanelBas: TPanel;
    Button1: TButton;
    PanelGauche: TPanel;
    Panel6: TPanel;
    lblchoixId: TLabel;
    cbIdUser: TComboBox;
    procedure FormCreate(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Déclarations privées }
  public
    { Déclarations publiques }
  end;

var
  DeleteUserForm: TDeleteUserForm;

implementation

{$R *.dfm}

procedure TDeleteUserForm.Button1Click(Sender: TObject);
var
 lHTTP: TIdHTTP;
 chaine,res : string;
begin
  lHTTP := TIdHTTP.Create;
  try
    chaine := 'http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/deleteUser/' + cbIdUser.Text;
    res := lHTTP.Get(chaine);
    ShowMessage(res);
  finally
    lHTTP.Free;
  end;
end;
//------------------------------------------------------------------------------
procedure TDeleteUserForm.FormCreate(Sender: TObject);
var
  lhttp : TIdHTTP;
  result : TStringList;
  i : Integer;
begin
  lHTTP := TIdHTTP.Create;
  result := TStringList.Create;
  try

  // lancer une fonction récupérant les identifiants des utilisateurs et les mettre dans la combobox
    result.CommaText := lHTTP.Get('http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/getUsersId');

    for i := 0 to result.Count-1 do
    begin
      result[i] := stringreplace(result[i], '[', '',[rfReplaceAll, rfIgnoreCase]);
      result[i] := stringreplace(result[i], ']', '',[rfReplaceAll, rfIgnoreCase]);
      cbIdUser.Items.Add(result[i]);
    end;

  finally
    result.Free;
    lhttp.Free;
  end;
end;
end.
