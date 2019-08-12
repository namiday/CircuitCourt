unit UpdateUser;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs,IDHTTP, Vcl.StdCtrls, Vcl.ExtCtrls;

type
  TUpdateUserForm = class(TForm)
    lblTitreUpdateUser: TLabel;
    PanelBas: TPanel;
    btnMajUser: TButton;
    PanelDroit: TPanel;
    Panel13: TPanel;
    Label8: TLabel;
    EditContact: TEdit;
    Panel11: TPanel;
    Label9: TLabel;
    EditshopKind: TEdit;
    Panel10: TPanel;
    Label10: TLabel;
    EditPostalCode: TEdit;
    Panel9: TPanel;
    Label11: TLabel;
    EditShopStreet: TEdit;
    Panel8: TPanel;
    Label12: TLabel;
    EditShopcity: TEdit;
    Panel7: TPanel;
    Label13: TLabel;
    EditShopName: TEdit;
    Panel12: TPanel;
    Label2: TLabel;
    editshopDescription: TEdit;
    Panel15: TPanel;
    Label14: TLabel;
    EditLatitude: TEdit;
    Panel14: TPanel;
    Label15: TLabel;
    EditLongitude: TEdit;
    PanelGauche: TPanel;
    Panel5: TPanel;
    lblMail: TLabel;
    editPassword: TEdit;
    Panel4: TPanel;
    Label1: TLabel;
    EditNickName: TEdit;
    Panel35: TPanel;
    Label3: TLabel;
    EditLastName: TEdit;
    Panel2: TPanel;
    Label5: TLabel;
    editFirstName: TEdit;
    Panel1: TPanel;
    Label6: TLabel;
    EditMail: TEdit;
    Panel6: TPanel;
    lblIsSeller: TLabel;
    cbIsSeller: TCheckBox;
    Panel3: TPanel;
    Panel23: TPanel;
    Label7: TLabel;
    EditTelShop: TEdit;
    Panel16: TPanel;
    lblchoixId: TLabel;
    cbIdUser: TComboBox;
    procedure btnMajUserClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure FormResize(Sender: TObject);
    procedure cbIsSellerClick(Sender: TObject);
  private
    { D�clarations priv�es }
  public
    { D�clarations publiques }
  end;

var
  UpdateUserForm: TUpdateUserForm;

implementation

{$R *.dfm}

procedure TUpdateUserForm.btnMajUserClick(Sender: TObject);
var
 lHTTP: TIdHTTP;
 chaine,res : string;
begin
  lHTTP := TIdHTTP.Create;
  try
    if cbIsSeller.Checked then
    begin
        chaine := 'http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/updateUser/'+ cbIdUser.Text + '/' + EditMail.Text +'/'+ editPassword.Text + '/' + editFirstName.Text + '/'
    + EditLastName.Text + '/' + EditNickName.Text + '/' + EditShopName.Text + '/' + EditShopcity.Text + '/' + EditShopStreet.Text + '/1/' + EditPostalCode.Text + '/'
    + EditshopKind.Text + '/' + editshopDescription.Text + '/' + EditContact.text + '/' + EditTelShop.Text +  '/' + EditLongitude.Text + '/' + EditLatitude.Text
    end
    else
    begin
    chaine := 'http://ec2-34-247-50-129.eu-west-1.compute.amazonaws.com/updateUser/'+ cbIdUser.Text + '/' +
     EditMail.Text +'/'+ editPassword.Text + '/' + editFirstName.Text + '/'
    + EditLastName.Text + '/' + EditNickName.Text;
    end;
    chaine :=  stringreplace(chaine, '�', 'o',[rfReplaceAll, rfIgnoreCase]);
    chaine :=  stringreplace(chaine, '�', 'e',[rfReplaceAll, rfIgnoreCase]);
    res := lHTTP.Get(chaine);
    ShowMessage(res);
  finally
    lHTTP.Free;
  end;
end;
//------------------------------------------------------------------------------


procedure TUpdateUserForm.cbIsSellerClick(Sender: TObject);
begin
 if cbIsSeller.Checked = true then
 begin
   EditshopKind.Enabled := true;
   EditShopStreet.Enabled := true;
   EditShopcity.Enabled := true;
   EditShopName.Enabled := true;
   editshopDescription.Enabled := true;
   EditContact.Enabled := true;
   EditPostalCode.Enabled := true;
   EditLongitude.Enabled := true;
   EditLatitude.Enabled := true;
   EditTelShop.Enabled := true;
 end
 else if cbIsSeller.Checked = false then
 begin
   EditshopKind.Enabled := false;
   EditShopStreet.Enabled := false;
   EditShopcity.Enabled := false;
   EditShopName.Enabled := false;
   editshopDescription.Enabled := false;
   EditContact.Enabled := false;
   EditPostalCode.Enabled := false;
   EditLongitude.Enabled := false;
   EditLatitude.Enabled := false;
   EditTelShop.Enabled := false;
 end;
end;

procedure TUpdateUserForm.FormCreate(Sender: TObject);
var
  lhttp : TIdHTTP;
  result : TStringList;
  i : Integer;
begin
// PanelGauche.Create(UpdateUserForm);
// PanelDroit.Create(UpdateUserForm);
// PanelGauche.Width := Round((UpdateUserForm.Width/2)-10);
// PanelDroit.Width := Round((UpdateUserForm.Width/2)-10);

  lHTTP := TIdHTTP.Create;
  result := TStringList.Create;
  try

  // lancer une fonction r�cup�rant les identifiants des utilisateurs et les mettre dans la combobox
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

procedure TUpdateUserForm.FormResize(Sender: TObject);
begin
//PanelGauche.Width := Round((UpdateUserForm.Width/2)-10);
//PanelDroit.Width := Round((UpdateUserForm.Width/2)-10);
end;

end.
